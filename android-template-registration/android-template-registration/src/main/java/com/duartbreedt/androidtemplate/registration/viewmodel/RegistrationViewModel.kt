package com.duartbreedt.androidtemplate.registration.viewmodel

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.duartbreedt.androidtemplate.registration.model.RegistrationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var registrationRepository: RegistrationRepository

    val usernameObservable: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val colorObservable: MutableLiveData<Color> by lazy {
        MutableLiveData<Color>()
    }

    private var username: String? = null
    private var color: Color? = null

    fun postUsername() {
        viewModelScope.launch(Dispatchers.IO) {
            usernameObservable.postValue(username)
        }
    }

    fun setUsername(username: String) {
        this.username = username
        postUsername()
    }

    fun postColor() {
        viewModelScope.launch(Dispatchers.IO) {
            colorObservable.postValue(color)
        }
    }

    fun setColor(color: Color) {
        this.color = color
        postColor()
    }
}