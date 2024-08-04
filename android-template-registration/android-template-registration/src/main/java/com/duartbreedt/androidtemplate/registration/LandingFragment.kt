package com.duartbreedt.androidtemplate.registration

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.duartbreedt.androidtemplate.ComposeFragment
import com.duartbreedt.androidtemplate.navigate

class LandingFragment : ComposeFragment() {

    @Composable
    override fun FragmentContent() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Please input your username below")
            Spacer(modifier = Modifier.height(8.dp))
            Username()
            Spacer(modifier = Modifier.height(8.dp))
            Continue()
        }
    }

    @Composable
    fun Continue() {
        Button(onClick = {
            navigateToPersonalizeProfile()
        }) {
            Text(text = "Continue", color = MaterialTheme.colorScheme.onPrimary)
        }
    }

    @Composable
    fun Username() {
        var text: String by remember { mutableStateOf("") }

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Username") }
        )
    }

    private fun navigateToPersonalizeProfile() {
        navigate(R.id.personalizeProfile)
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