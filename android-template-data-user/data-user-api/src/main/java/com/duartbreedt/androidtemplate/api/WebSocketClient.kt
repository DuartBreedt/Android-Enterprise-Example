package com.duartbreedt.androidtemplate.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.http.websocket.websocketServerAccept
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object WebSocketClient {

    private var _instance: HttpClient? = null

    val instance: HttpClient
        get() {
            if (_instance == null) {
                _instance = createClient()
            }

            return _instance!!
        }

    private fun createClient(): HttpClient {
        return HttpClient(OkHttp) {
            install(WebSockets) {
                contentConverter = KotlinxWebsocketSerializationConverter(Json)
            }
        }
    }
}