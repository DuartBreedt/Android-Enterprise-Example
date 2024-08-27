package com.duartbreedt.androidtemplate.routing

import com.duartbreedt.androidtemplate.model.ExposedUser
import com.duartbreedt.androidtemplate.model.Message
import com.duartbreedt.androidtemplate.model.UserService
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

fun Application.configureSocketRouting(userService: UserService) {

    val messageResponseFlow: MutableSharedFlow<Message> = MutableSharedFlow()
    val sharedFlow = messageResponseFlow.asSharedFlow()

    routing {

        webSocket("/connect/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val user = userService.read(id)
            if (user == null) {
                close(CloseReason(CloseReason.Codes.CANNOT_ACCEPT, "User not found!"))
                return@webSocket
            }

            send("You are connected to the AndroidTemplate API")

            with(handleOutgoingMessages(user, sharedFlow)) {
                handleIncomingMessages(id, user, this, messageResponseFlow)
            }
        }
    }
}

suspend fun WebSocketServerSession.handleOutgoingMessages(user: ExposedUser?, sharedFlow: SharedFlow<Message>): Job {
    return launch {
        sharedFlow.collect { message ->
            sendSerialized("${user?.name}: ${message.message}")
        }
    }
}

suspend fun WebSocketSession.handleIncomingMessages(id: Int, user: ExposedUser, job: Job, messageResponseFlow: MutableSharedFlow<Message>) {
    runCatching {
        incoming.consumeAsFlow()
            .mapNotNull { (it as? Frame.Text)?.readText() }
            .collect { messageResponseFlow.emit(Message(id, user, it)) }
    }.onFailure { exception ->
        println("WebSocket exception: ${exception.localizedMessage}")
    }.also {
        job.cancel()
    }
}

