package com.duartbreedt.androidtemplate.api

import androidx.compose.ui.graphics.Color
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.converter
import io.ktor.client.plugins.websocket.sendSerialized
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.http.HttpMethod
import io.ktor.serialization.suitableCharset
import io.ktor.util.reflect.typeInfo
import io.ktor.websocket.close
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.serialization.Serializable
import javax.inject.Inject

class ChatRepository @Inject constructor() {

    suspend fun endConnection(session:DefaultClientWebSocketSession?) {
        session?.close()
    }

    suspend fun connect(id: Int?): DefaultClientWebSocketSession? {
        return try {
            WebSocketClient.instance.webSocketSession(
                method = HttpMethod.Get, host = "localhost", port = 8080, path = "/connect"
            ) {
                url {
                    parameters.append("id", id.toString())
                }
            }
        } catch (e: Exception) {
            // TODO Handle exception
            null
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    suspend fun receiveMessages(session: DefaultClientWebSocketSession, block: (message: Message) -> Unit) {
        session.incoming.consumeAsFlow()
            .mapNotNull {
                if (session.converter?.isApplicable(it) != true) return@mapNotNull null
                val charset = session.call.request.headers.suitableCharset()
                val typeInfo = typeInfo<MessageResponse>()
                session.converter!!.deserialize(charset, typeInfo, it) as MessageResponse
            }
            .collect { block(Message(it.userId, User(it.user.username, Color(it.user.color.hexToULong())), it.message)) }
    }

    suspend fun sendMessage(session: DefaultClientWebSocketSession, message: String) {
        @Serializable
        data class MessageRequest(val message: String)

        try {
            session.sendSerialized(MessageRequest(message))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}