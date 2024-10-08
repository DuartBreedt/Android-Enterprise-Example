package com.duartbreedt.androidtemplate.registration.view

import android.content.res.Configuration
import android.view.KeyEvent.KEYCODE_ENTER
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.duartbreedt.androidtemplate.ComposeFragment
import com.duartbreedt.androidtemplate.Event
import com.duartbreedt.androidtemplate.PrimaryButton
import com.duartbreedt.androidtemplate.navigateToRes
import com.duartbreedt.androidtemplate.registration.R
import com.duartbreedt.androidtemplate.registration.viewmodel.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingFragment : ComposeFragment() {

    private val registrationViewModel: RegistrationViewModel by activityViewModels<RegistrationViewModel>()

    private fun onContinuePressed(newUsername: String): Boolean {
        registrationViewModel.setUsername(newUsername)
        findNavController().navigateToRes(R.id.personalizeProfile)
        return true
    }

    @Composable
    override fun FragmentContent() {

        val username: Event<String>? by registrationViewModel.usernameObservable.observeAsState()
        var newUsername: String by rememberSaveable { mutableStateOf(username?.peekContent() ?: "") }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Please input your username below")
            Spacer(modifier = Modifier.height(8.dp))
            Username(newUsername) {
                newUsername = it
            }
            Spacer(modifier = Modifier.height(8.dp))
            PrimaryButton("Continue") {
                onContinuePressed(newUsername)
            }
        }
    }


    @Composable
    fun Username(username: String, onUsernameChanged: (String) -> Unit) {
        TextField(
            value = username,
            singleLine = true,
            onValueChange = { onUsernameChanged(it) },
            keyboardActions = KeyboardActions(
                onDone = { onContinuePressed(username) }
            ),
            modifier = Modifier.onKeyEvent {
                if (it.nativeKeyEvent.keyCode == KEYCODE_ENTER) onContinuePressed(username) else false
            },
            label = { Text("Username") }
        )
    }

    //region Previews
    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    override fun PreviewFragmentContent() {
        ComposeFragmentWrapper()
    }
    //endregion
}