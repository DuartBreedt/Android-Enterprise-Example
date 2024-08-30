package com.duartbreedt.androidtemplate

open class Event<out T> {

    companion object {
        fun <T> empty(): Event<T> {
            return Event<T>(null, true).apply { hasBeenHandled = true }
        }
    }

    private constructor(content: T?, empty: Boolean) {
        this@Event.content = if (empty) null else content
    }

    constructor(content: T) : this(content, false)

    private val content: T?

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T? = content
}