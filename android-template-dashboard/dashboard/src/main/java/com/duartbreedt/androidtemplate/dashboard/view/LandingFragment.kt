package com.duartbreedt.androidtemplate.dashboard.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.duartbreedt.androidtemplate.ComposeFragment
import com.duartbreedt.androidtemplate.api.User
import com.duartbreedt.androidtemplate.dashboard.data.R
import com.duartbreedt.androidtemplate.dashboard.viewmodel.DashboardViewModel
import com.duartbreedt.androidtemplate.navigateToDeeplink
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingFragment : ComposeFragment() {

    private val dashboardViewModel: DashboardViewModel by activityViewModels<DashboardViewModel>()

    override fun onStart() {
        super.onStart()

        dashboardViewModel.getUser()
        dashboardViewModel.getMessages()
    }

    @Composable
    override fun FragmentContent() {

        val user: User? by dashboardViewModel.userObservable.observeAsState()

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Dashboard: ${user?.username} - ${user?.color}")
        }

        LazyColumn {
            items(dashboardViewModel.messages) { message ->
                Text(message.toString())
            }
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