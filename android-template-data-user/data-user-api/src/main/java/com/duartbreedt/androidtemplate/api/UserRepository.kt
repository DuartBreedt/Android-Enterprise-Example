package com.duartbreedt.androidtemplate.api

import javax.inject.Inject

class UserRepository @Inject constructor() {

    fun setUser(user: User): Boolean {
        UserSession.username = user.username
        UserSession.color = user.color

        return true
    }

    fun getUser(): User? {

        if (!UserSession.isSet) {
            return null
        }

        return User(
            UserSession.username!!,
            UserSession.color!!
        )
    }
}