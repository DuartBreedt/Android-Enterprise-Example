package com.duartbreedt.androidtemplate

import com.duartbreedt.androidtemplate.model.UserService
import com.duartbreedt.androidtemplate.plugins.configureDatabases
import com.duartbreedt.androidtemplate.routing.configureRouting
import com.duartbreedt.androidtemplate.plugins.configureSerialization
import com.duartbreedt.androidtemplate.plugins.configureSockets
import com.duartbreedt.androidtemplate.routing.configureSocketRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureSockets()
    val database = configureDatabases()

    // TODO Use DI
    val userService = UserService(database)

    configureRouting(userService)
    configureSocketRouting(userService)
}
