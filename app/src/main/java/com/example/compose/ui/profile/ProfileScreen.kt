package com.example.compose.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.example.compose.ui.theme.ComposeTheme

@Composable
fun ProfileScreen(profileScreenViewModel: ProfileScreenViewModel) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val usernameFlowLifecycleAware = remember(profileScreenViewModel.usernameFlow, lifecycleOwner) {
        profileScreenViewModel.usernameFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val userStateFlow = usernameFlowLifecycleAware.collectAsState(initial = "loading...")
    val userStateLiveState = profileScreenViewModel.usernameLiveData.observeAsState("loading...")

    Surface(color = MaterialTheme.colors.primary, modifier = Modifier.fillMaxSize()) {
        ComposeTheme {
            Column {
                Text(text = "Flow value: ${userStateFlow.value}", modifier = Modifier.padding(16.dp, 16.dp))
                Text(text = "Live data value: ${userStateLiveState.value}", modifier = Modifier.padding(16.dp, 16.dp))
            }
        }
    }
}
