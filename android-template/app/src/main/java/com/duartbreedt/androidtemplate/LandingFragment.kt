package com.duartbreedt.androidtemplate

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.ActivityNavigator
import androidx.navigation.fragment.findNavController
import com.duartbreedt.androidtemplate.registration.RegistrationActivity

class LandingFragment : ComposeFragment() {

    @Composable
    override fun FragmentContent() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Greeting("Welcome to Android Template")
            Spacer(modifier = Modifier.height(8.dp))
            Register()
        }
    }

    @Composable
    fun Greeting(text: String, modifier: Modifier = Modifier) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = modifier
        )
    }

    @Composable
    fun Register() {
        Button(onClick = {
            startRegistrationActivity()
        }) {
            Text(text = "Register", color = MaterialTheme.colorScheme.onPrimary)
        }
    }

    private fun startRegistrationActivity() {

        // TODO: Use DI to provide this implementation on the Registration Module.
        val activityNavigator: ActivityNavigator = findNavController().navigatorProvider.getNavigator(ActivityNavigator::class.java)
        val newDestination: ActivityNavigator.Destination = activityNavigator.createDestination().apply {
            setIntent(Intent(requireContext(), RegistrationActivity::class.java))
            id = com.duartbreedt.androidtemplate.registration.R.id.registrationActivity
            addDeepLink(getString(com.duartbreedt.androidtemplate.registration.R.string.deeplink_registration_landing))
        }
        findNavController().graph.addDestination(newDestination)

        navigate(getString(com.duartbreedt.androidtemplate.registration.R.string.deeplink_registration_landing))
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

