package com.duartbreedt.androidtemplate.api

import androidx.compose.ui.graphics.Color

internal object UserSession {
    var username: String? = null
    var color: Color? = null

    val isSet: Boolean
        get() = username != null && color != null
}
