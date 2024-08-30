package com.duartbreedt.androidtemplate.api

object UserSession {
    var id: Int? = null

    val isSet: Boolean
        get() = id != null
}
