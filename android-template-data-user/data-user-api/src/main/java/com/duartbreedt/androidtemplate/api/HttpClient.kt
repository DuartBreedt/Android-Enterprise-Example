package com.duartbreedt.androidtemplate.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

object HttpClient {

    private var _instance: HttpClient? = null

    val instance: HttpClient
        get() {
            if (_instance == null) {
                _instance = createClient()
            }

            return _instance!!
        }

    private fun createClient(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json()
            }
        }
    }
}