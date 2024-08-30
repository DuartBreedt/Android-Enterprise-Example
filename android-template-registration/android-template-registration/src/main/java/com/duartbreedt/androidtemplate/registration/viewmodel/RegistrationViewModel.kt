package com.duartbreedt.androidtemplate.registration.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duartbreedt.androidtemplate.Event
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

    val usernameObservable: MutableLiveData<Event<String>> by lazy {
        MutableLiveData<Event<String>>()
    }

    val colorObservable: MutableLiveData<Event<Color>> by lazy {
        MutableLiveData<Event<Color>>()
    }

    val registrationStatusObservable: MutableLiveData<Event<Boolean>> by lazy {
        MutableLiveData<Event<Boolean>>()
    }

    fun setUsername(username: String) {
        postUsername(username)
    }

    fun setColor(color: Color) {
        postColor(color)
    }

    fun register(username: String?, color: Color?) {
        viewModelScope.launch(Dispatchers.IO) {
            val id: Int? =
                if (username != null && color != null) userRepository.setUser(User(username, color.toString()))
                else null

            UserSession.id = id

            postRegistrationStatus(id != null)
        }
    }

    fun clearValues() {
        usernameObservable.value = Event.empty()
        colorObservable.value = Event.empty()
        registrationStatusObservable.value = Event.empty()
    }

    private fun postUsername(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            usernameObservable.postValue(Event(username))
        }
    }

    private fun postColor(color: Color) {
        viewModelScope.launch(Dispatchers.IO) {
            colorObservable.postValue(Event(color))
        }
    }

    private fun postRegistrationStatus(status: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            registrationStatusObservable.postValue(Event(status))
        }
    }
}