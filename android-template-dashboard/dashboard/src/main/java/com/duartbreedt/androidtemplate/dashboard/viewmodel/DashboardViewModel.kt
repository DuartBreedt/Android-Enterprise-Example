package com.duartbreedt.androidtemplate.dashboard.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duartbreedt.androidtemplate.api.ChatRepository
import com.duartbreedt.androidtemplate.api.MessageResponse
import com.duartbreedt.androidtemplate.api.User
import com.duartbreedt.androidtemplate.api.UserRepository
import com.duartbreedt.androidtemplate.api.UserSession
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val chatRepository: ChatRepository
) : ViewModel() {

    private var session: DefaultClientWebSocketSession? = null

    val userObservable: MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }

    val messages: SnapshotStateList<MessageResponse> = mutableStateListOf()

    fun getUser() {
        postUser()
    }

    fun getMessages() {
        viewModelScope.launch(Dispatchers.IO) {
            if (session == null) {
                session = chatRepository.connect(UserSession.id)
            }

            if (session == null) {
                // TODO Handle the error
                return@launch
            }

            chatRepository.receiveMessages(session!!) { messages.add(it) }
        }
    }

    fun sendMessage(message: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (session == null) {
                session = chatRepository.connect(UserSession.id)
            }

            session?.let { chatRepository.sendMessage(it, message) }
        }
    }

    private fun postUser() {
        viewModelScope.launch(Dispatchers.IO) {
            userObservable.postValue(userRepository.getUser(UserSession.id))
        }
    }
}