package com.example.compose.ui.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.compose.ui.theme.ComposeTheme

@Composable
fun ProfileScreen() {
    Surface(color = MaterialTheme.colors.background) {
        ComposeTheme {
            Text(text = "Profile Screen", modifier = Modifier.padding(16.dp, 16.dp))
        }
    }
}