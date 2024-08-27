package com.duartbreedt.androidtemplate.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(val id: Int, val user: ExposedUser, val message: String)