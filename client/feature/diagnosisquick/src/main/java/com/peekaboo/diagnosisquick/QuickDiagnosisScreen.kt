package com.peekaboo.diagnosisquick

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.peekaboo.design_system.White3

@Composable
fun QuickDiagnosisScreen() {
    QuickDiagnosisContent()
}

@Composable
fun QuickDiagnosisContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White3)
    ) {
        //
    }
}

@Composable
fun PreviewQuickDiagnosis() {
    QuickDiagnosisContent()
}