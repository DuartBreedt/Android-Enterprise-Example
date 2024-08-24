package com.duartbreedt.androidtemplate.dashboard.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.duartbreedt.androidtemplate.api.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val usernameObservable: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val colorObservable: MutableLiveData<Color> by lazy {
        MutableLiveData<Color>()
    }
}