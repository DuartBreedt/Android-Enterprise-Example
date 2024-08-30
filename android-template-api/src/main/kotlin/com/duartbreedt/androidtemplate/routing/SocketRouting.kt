package com.duartbreedt.androidtemplate.routing

import com.duartbreedt.androidtemplate.model.ExposedUser
import com.duartbreedt.androidtemplate.model.Message
import com.duartbreedt.androidtemplate.model.UserService
import io.ktor.serialization.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.util.reflect.*
import io.ktor.websocket.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

fun Application.configureSocketRouting(userService: UserService) {

    val messageResponseFlow: MutableSharedFlow<Message> = MutableSharedFlow()
    val sharedFlow = messageResponseFlow.asSharedFlow()

    routing {

        webSocket("/connect") {
            val id = call.request.queryParameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val user = userService.read(id)
            if (user == null) {
                close(CloseReason(CloseReason.Codes.CANNOT_ACCEPT, "User not found!"))
                return@webSocket
            }

            with(handleOutgoingMessages(sharedFlow)) {
                handleIncomingMessages(id, user, this, messageResponseFlow)
            }
        }
    }
}

suspend fun WebSocketServerSession.handleOutgoingMessages(sharedFlow: SharedFlow<Message>): Job {
    @Serializable
    data class Response(val userId: Int, val user: ExposedUser, val message: String)

    return launch {
        sharedFlow.collect { message ->
            sendSerialized(Response(message.id, message.user, message.message))
        }
    }
}

suspend fun WebSocketServerSession.handleIncomingMessages(id: Int, user: ExposedUser, job: Job, messageResponseFlow: MutableSharedFlow<Message>) {
    @Serializable
    data class MessageResponse(val message: String)

    runCatching {
        incoming.consumeAsFlow()
            .mapNotNull {
                if (converter?.isApplicable(it) != true) return@mapNotNull null
                val charset = call.request.headers.suitableCharset()
                val typeInfo = typeInfo<MessageResponse>()
                converter!!.deserialize(charset, typeInfo, it) as MessageResponse
            }
            .collect { messageResponseFlow.emit(Message(id, user, it.message)) }
    }.onFailure { exception ->
        println("WebSocket exception: ${exception.localizedMessage}")
    }.also {
        job.cancel()
    }
}

