package com.example.compose.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.compose.ui.theme.ComposeTheme

@Composable
fun MainScreen() {
    Surface(color = MaterialTheme.colors.background) {
        ComposeTheme {
            Text(text = "Main Screen", modifier = Modifier.padding(16.dp, 16.dp))
        }
    }
}
