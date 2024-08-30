package com.duartbreedt.androidtemplate.api

import kotlinx.serialization.Serializable

@Serializable
data class MessageResponse(val username: String, val message: String)