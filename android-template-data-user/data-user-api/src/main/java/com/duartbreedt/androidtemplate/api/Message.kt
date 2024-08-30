package com.duartbreedt.androidtemplate.api

data class Message(
    val userId: Int,
    val user: User,
    val message: String
)