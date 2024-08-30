package com.duartbreedt.androidtemplate.api

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String,
    val color: String
)
