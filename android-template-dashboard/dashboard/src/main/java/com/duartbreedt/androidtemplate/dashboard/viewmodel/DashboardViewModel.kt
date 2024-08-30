package com.duartbreedt.androidtemplate.dashboard.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duartbreedt.androidtemplate.api.ChatRepository
import com.duartbreedt.androidtemplate.api.Message
import com.duartbreedt.androidtemplate.api.UserSession
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {

    private var session: DefaultClientWebSocketSession? = null
    private var receiveMessagesJob: Job? = null

    val messages: SnapshotStateList<Message> = mutableStateListOf()

    fun getMessages() {
        receiveMessagesJob = viewModelScope.launch(Dispatchers.IO) {
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

    fun closeSession() {
        viewModelScope.launch(Dispatchers.IO) {
            chatRepository.endConnection(session)
            receiveMessagesJob?.cancel()
            session = null
        }
    }
}