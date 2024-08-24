package com.duartbreedt.androidtemplate.registration.view

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.duartbreedt.androidtemplate.ComposeFragment
import com.duartbreedt.androidtemplate.PrimaryButton
import com.duartbreedt.androidtemplate.navigateToDeeplink
import com.duartbreedt.androidtemplate.registration.viewmodel.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.duartbreedt.androidtemplate.dashboard.data.R as DashboardR

@AndroidEntryPoint
class PersonalizeProfileFragment : ComposeFragment() {

    private val registrationViewModel: RegistrationViewModel by activityViewModels<RegistrationViewModel>()

    private val registrationStatusObserver: (value: Boolean) -> Unit = { success ->
        if (success) {
            findNavController().navigateToDeeplink(getString(DashboardR.string.deeplink_dashboard_landing))
        } else {
            // TODO: Do something about this
        }
    }

    override fun onStart() {
        super.onStart()

        registrationViewModel.registrationStatusObservable.observe(viewLifecycleOwner, registrationStatusObserver)
    }

    override fun onStop() {
        super.onStop()

        registrationViewModel.registrationStatusObservable.removeObserver(registrationStatusObserver)
    }

    @Composable
    override fun FragmentContent() {

        val username: String? by registrationViewModel.usernameObservable.observeAsState()
        val color: Color? by registrationViewModel.colorObservable.observeAsState()

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(username ?: "", color = color ?: MaterialTheme.colorScheme.onSurface)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Select your color")
            Row {
                ClickableCircle(Color.Red) {
                    registrationViewModel.setColor(it)
                }
                ClickableCircle(Color.Blue) {
                    registrationViewModel.setColor(it)
                }
                ClickableCircle(Color.Green) {
                    registrationViewModel.setColor(it)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            PrimaryButton("Continue") {
                registrationViewModel.register()
            }
        }
    }

    @Composable
    fun ClickableCircle(color: Color, onColorChanged: (Color) -> Unit) {
        Box(
            modifier = Modifier
                .padding(10.dp, 0.dp)
                .height(20.dp)
                .aspectRatio(1f)
                .background(color, shape = CircleShape)
                .clickable {
                    onColorChanged(color)
                }
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