package com.duartbreedt.androidtemplate.model

import kotlinx.serialization.Serializable

@Serializable
data class ExposedUser(val username: String, val color: String)