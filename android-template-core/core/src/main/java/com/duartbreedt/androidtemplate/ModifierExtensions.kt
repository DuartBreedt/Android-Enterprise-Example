package com.duartbreedt.androidtemplate

import androidx.compose.ui.Modifier

fun Modifier.condition(condition: Boolean, ifTrue: Modifier.() -> Modifier, ifFalse: (Modifier.() -> Modifier)? = null): Modifier =
    if (condition) then(ifTrue(Modifier)) else if (ifFalse != null) then(ifFalse(Modifier)) else this


fun <T> Modifier.safely(value: T?, ifSafe: Modifier.(T) -> Modifier): Modifier =
    if (value != null) then(ifSafe(Modifier, value)) else this