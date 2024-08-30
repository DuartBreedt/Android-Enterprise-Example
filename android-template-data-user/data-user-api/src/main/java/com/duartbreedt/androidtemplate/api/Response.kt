package com.duartbreedt.androidtemplate.api

import kotlinx.serialization.Serializable

@Serializable
internal data class MessageResponse(
    val userId: Int,
    val user: UserResponse,
    val message: String
)


@Serializable
internal data class UserResponse(
    val username: String,
    val color: String
)

