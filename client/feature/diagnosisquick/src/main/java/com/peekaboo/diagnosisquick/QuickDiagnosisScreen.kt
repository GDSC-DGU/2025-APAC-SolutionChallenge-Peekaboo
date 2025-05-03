package com.peekaboo.diagnosisquick

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.peekaboo.design_system.QuickDiagnosisTitle
import com.peekaboo.design_system.White3
import com.peekaboo.ui.common.appbar.TopBar

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
        TopBar(
            titleText = QuickDiagnosisTitle,
            isIconValid = true
        )
    }
}

@Composable
fun PreviewQuickDiagnosis() {
    QuickDiagnosisContent()
}