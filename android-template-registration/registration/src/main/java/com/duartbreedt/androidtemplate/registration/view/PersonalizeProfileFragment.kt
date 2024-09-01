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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.duartbreedt.androidtemplate.ComposeFragment
import com.duartbreedt.androidtemplate.Event
import com.duartbreedt.androidtemplate.PrimaryButton
import com.duartbreedt.androidtemplate.fromHex
import com.duartbreedt.androidtemplate.navigateToDeeplink
import com.duartbreedt.androidtemplate.registration.R
import com.duartbreedt.androidtemplate.registration.viewmodel.RegistrationViewModel
import com.duartbreedt.androidtemplate.safely
import dagger.hilt.android.AndroidEntryPoint
import com.duartbreedt.androidtemplate.dashboard.data.R as DashboardR

@AndroidEntryPoint
class PersonalizeProfileFragment : ComposeFragment() {

    private val registrationViewModel: RegistrationViewModel by activityViewModels<RegistrationViewModel>()

    private val registrationStatusObserver: (value: Event<Boolean>) -> Unit = { event ->
        if (event.getContentIfNotHandled() == true) {
            registrationViewModel.clearValues()
            findNavController().navigateToDeeplink(getString(DashboardR.string.deeplink_dashboard_landing), R.id.registrationLandingFragment)
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

        val username: Event<String>? by registrationViewModel.usernameObservable.observeAsState()
        val color: Event<Color>? by registrationViewModel.colorObservable.observeAsState()

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = username?.peekContent() ?: "",
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
                    .safely(color?.peekContent()) { Modifier.background(it) }
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Select your color")
            Row(Modifier.padding(bottom = 16.dp)) {
                ClickableCircle(Color.fromHex("EB9DA2"), registrationViewModel::setColor)
                ClickableCircle(Color.fromHex("F0B884"), registrationViewModel::setColor)
                ClickableCircle(Color.fromHex("E8E6A5"), registrationViewModel::setColor)
            }
            Row {
                ClickableCircle(Color.fromHex("BBE8B5"), registrationViewModel::setColor)
                ClickableCircle(Color.fromHex("ACBBE8"), registrationViewModel::setColor)
                ClickableCircle(Color.fromHex("C5ACE8"), registrationViewModel::setColor)
            }
            Spacer(modifier = Modifier.height(8.dp))
            PrimaryButton("Continue") {
                registrationViewModel.register(username?.peekContent(), color?.peekContent())
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
                .clickable { onColorChanged(color) }
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