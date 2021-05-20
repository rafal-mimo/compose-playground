package com.example.compose.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.ui.theme.ComposeTheme

@Composable
fun MainScreen(mainScreenViewModel: MainScreenViewModel) {
    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
        ComposeTheme {
            ItemList()
        }
    }
}

@Composable
private fun ItemList() {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(60) {
            Text(text = "Item", fontSize = 24.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ItemList()
}
