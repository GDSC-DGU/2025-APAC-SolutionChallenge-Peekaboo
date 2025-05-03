package com.peekaboo.diagnosis.selectarea

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.peekaboo.design_system.DiagnosisTitle
import com.peekaboo.design_system.White3
import com.peekaboo.ui.common.appbar.TopBar

@Composable
fun SelectAreaScreen() {
    SelectAreaContent()
}

@Composable
fun SelectAreaContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White3)
    ) {
        TopBar(
            titleText = DiagnosisTitle,
            isIconValid = true
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSelectArea() {
    SelectAreaContent()
}