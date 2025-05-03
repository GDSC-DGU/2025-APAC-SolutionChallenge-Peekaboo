package com.peekaboo.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.peekaboo.design_system.White3

@Composable
fun HomeScreen() {
    HomeContent()
}

@Composable
fun HomeContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White3)
    ) {}
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHome() {
    HomeContent()
}