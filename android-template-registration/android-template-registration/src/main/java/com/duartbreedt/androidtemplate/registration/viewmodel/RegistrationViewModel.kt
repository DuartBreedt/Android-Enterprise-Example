package com.duartbreedt.androidtemplate.registration.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duartbreedt.androidtemplate.api.User
import com.duartbreedt.androidtemplate.api.UserRepository
import com.duartbreedt.androidtemplate.api.UserSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val usernameObservable: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val colorObservable: MutableLiveData<Color> by lazy {
        MutableLiveData<Color>()
    }

    val registrationStatusObservable: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    private var username: String? = null
    private var color: Color? = null

    fun setUsername(username: String) {
        this.username = username
        postUsername()
    }

    fun setColor(color: Color) {
        this.color = color
        postColor()
    }

    fun register() {
        viewModelScope.launch(Dispatchers.IO) {
            val id: Int? =
                if (username != null && color != null) userRepository.setUser(User(username!!, color.toString()))
                else null

            UserSession.id = id

            postRegistrationStatus(id != null)
        }
    }

    private fun postUsername() {
        viewModelScope.launch(Dispatchers.IO) {
            usernameObservable.postValue(username)
        }
    }

    private fun postColor() {
        viewModelScope.launch(Dispatchers.IO) {
            colorObservable.postValue(color)
        }
    }

    private fun postRegistrationStatus(status: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            registrationStatusObservable.postValue(status)
        }
    }
}