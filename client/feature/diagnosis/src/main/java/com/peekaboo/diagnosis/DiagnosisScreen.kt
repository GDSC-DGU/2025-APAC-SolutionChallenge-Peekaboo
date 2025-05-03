package com.peekaboo.diagnosis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.peekaboo.design_system.White3

@Composable
fun DiagnosisScreen() {
    DiagnosisContent()
}

@Composable
fun DiagnosisContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White3)
    ) {
        //
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDiagnosis() {
    DiagnosisContent()
}