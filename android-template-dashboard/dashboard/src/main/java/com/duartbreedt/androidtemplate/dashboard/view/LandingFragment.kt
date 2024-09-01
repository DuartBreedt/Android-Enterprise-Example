package com.duartbreedt.androidtemplate.dashboard.view

import android.content.res.Configuration
import android.view.KeyEvent.KEYCODE_ENTER
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.activityViewModels
import com.duartbreedt.androidtemplate.ComposeFragment
import com.duartbreedt.androidtemplate.api.Message
import com.duartbreedt.androidtemplate.api.UserSession
import com.duartbreedt.androidtemplate.condition
import com.duartbreedt.androidtemplate.dashboard.R
import com.duartbreedt.androidtemplate.dashboard.viewmodel.DashboardViewModel
import com.duartbreedt.androidtemplate.isBlankOrEmpty
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope

@AndroidEntryPoint
class LandingFragment : ComposeFragment() {

    private val dashboardViewModel: DashboardViewModel by activityViewModels<DashboardViewModel>()

    override fun onStart() {
        super.onStart()

        dashboardViewModel.getMessages()
    }

    override fun onStop() {
        super.onStop()

        dashboardViewModel.closeSession()
    }

    @Composable
    override fun FragmentContent() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val listState = rememberLazyListState()

            LaunchedEffect(dashboardViewModel.messages.size) {
                if (dashboardViewModel.messages.isNotEmpty()) {
                    coroutineScope {
                        listState.animateScrollToItem(index = dashboardViewModel.messages.size - 1)
                    }
                }
            }

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Bottom,
                state = listState
            ) {
                items(dashboardViewModel.messages) {
                    Message(it)
                }
            }

            MessageInput()
        }
    }

    @Composable
    private fun MessageInput() {
        val focusRequester = remember { FocusRequester() }

        var message: String by rememberSaveable { mutableStateOf("") }

        fun onButtonClicked(): Boolean {
            if (!message.isBlankOrEmpty()) {
                dashboardViewModel.sendMessage(message.trim())
                message = ""
            }

            return true
        }

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = message,
                label = null,
                onValueChange = { message = it },
                keyboardActions = KeyboardActions(onDone = { onButtonClicked() }),
                shape = CircleShape,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .onKeyEvent { if (it.nativeKeyEvent.keyCode == KEYCODE_ENTER) onButtonClicked() else false }
                    .focusRequester(focusRequester)
                    .padding(end = 8.dp)
                    .weight(1f)
            )
            IconButton(
                onClick = ::onButtonClicked,
                modifier = Modifier
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_send),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }

    @Composable
    private fun Message(message: Message) {
        val isMe = message.userId == UserSession.id

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .condition(isMe, { padding(start = 48.dp) }, { padding(end = 48.dp) }),
            horizontalAlignment = if (isMe) Alignment.End else Alignment.Start
        ) {
            Column(
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .condition(
                        isMe,
                        { clip(shape = RoundedCornerShape(16.dp, 16.dp, 8.dp, 16.dp)) },
                        { clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 8.dp)) })
                    .background(message.user.color)
                    .padding(vertical = 8.dp)
            ) {

                if (!isMe) {
                    Text(
                        text = message.user.username,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                }

                Text(
                    text = message.message,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
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