package com.duartbreedt.androidtemplate.dashboard.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.activityViewModels
import com.duartbreedt.androidtemplate.ComposeFragment
import com.duartbreedt.androidtemplate.dashboard.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingFragment : ComposeFragment() {

    private val dashboardViewModel: DashboardViewModel by activityViewModels<DashboardViewModel>()

    @Composable
    override fun FragmentContent() {

        val username: String? by dashboardViewModel.usernameObservable.observeAsState()
        val color: Color? by dashboardViewModel.colorObservable.observeAsState()

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Dashboard: $username - $color")
        }
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