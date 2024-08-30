package com.duartbreedt.androidtemplate.api

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.serialization.Serializable
import javax.inject.Inject

class UserRepository @Inject constructor() {

    suspend fun setUser(user: User): Int? {
        @Serializable
        data class Response(val id: Int)

        @Serializable
        data class Request(val username: String, val color: String)

        try {
            val response: HttpResponse = HttpClient.instance.post("http://localhost:8080/users") {
                contentType(ContentType.Application.Json)
                setBody(Request(user.username, user.color.toString()))
            }

            if (!response.status.isSuccess()) {
                // TODO Handle error
                return null
            }

            return response.body<Response>().id

        } catch (e: Exception) {
            // TODO Handle error
            return null
        }
    }

    suspend fun getUser(id: Int?): User? {

        try {
            val response: HttpResponse = HttpClient.instance.get("http://localhost:8080/users") {
                contentType(ContentType.Application.Json)
                url {
                    appendPathSegments(id.toString())
                }
            }

            if (!response.status.isSuccess()) {
                // TODO Handle error
                return null
            }

            return response.body()

        } catch (e: Exception) {
            // TODO Handle error
            return null
        }
    }
}