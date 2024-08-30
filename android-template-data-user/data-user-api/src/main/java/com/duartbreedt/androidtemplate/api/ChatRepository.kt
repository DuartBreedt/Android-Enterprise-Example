package com.duartbreedt.androidtemplate.api

import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.converter
import io.ktor.client.plugins.websocket.sendSerialized
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.serialization.suitableCharset
import io.ktor.util.reflect.typeInfo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.serialization.Serializable
import javax.inject.Inject

class ChatRepository @Inject constructor() {

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

    suspend fun receiveMessages(session: DefaultClientWebSocketSession, block: (message: MessageResponse) -> Unit) {
        session.incoming.consumeAsFlow()
            .mapNotNull {
                if (session.converter?.isApplicable(it) != true) return@mapNotNull null
                val charset = session.call.request.headers.suitableCharset()
                val typeInfo = typeInfo<MessageResponse>()
                session.converter!!.deserialize(charset, typeInfo, it) as MessageResponse
            }
            .collect { block(it) }
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