package com.peekaboo.diagnosishistory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.DiagnosingBtn
import com.peekaboo.design_system.DiagnosisEmptyTitle
import com.peekaboo.design_system.DiagnosisHistoryTitle
import com.peekaboo.design_system.Gray3
import com.peekaboo.design_system.R
import com.peekaboo.design_system.White3
import com.peekaboo.ui.common.appbar.TopBar
import com.peekaboo.ui.common.button.BottomRectangleBtn

@Composable
fun DiagnosisHistoryScreen(
    goToDiagnosisPage: () -> Unit
) {
    DiagnosisHistoryContent(
        onClickDiagnosingBtn = { goToDiagnosisPage() }
    )
}

@Composable
fun DiagnosisHistoryContent(
    onClickDiagnosingBtn: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White3)
    ) {
        TopBar(
            titleText = DiagnosisHistoryTitle,
            isIconValid = true
        )

        DiagnosisHistoryEmptyContent(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClickDiagnosingBtn = onClickDiagnosingBtn
        )
    }
}

@Composable
fun DiagnosisHistoryEmptyContent(
    modifier: Modifier,
    onClickDiagnosingBtn: () -> Unit
) {
    Image(
        painter = painterResource(id = R.drawable.ic_empty_box),
        contentDescription = "empty box",
        modifier = modifier
            .padding(top = 228.dp)
            .size(100.dp)
    )

    Text(
        text = DiagnosisEmptyTitle,
        color = Gray3,
        style = BaeBaeTypo.Caption3,
        modifier = modifier
            .padding(top = 17.dp)
    )

    Spacer(modifier = Modifier.height(30.dp))

    BottomRectangleBtn(
        horizontalPadding = 65,
        btnText = DiagnosingBtn,
        isBtnValid = true,
        onClickAction = onClickDiagnosingBtn
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDiagnosisHistory() {
    DiagnosisHistoryContent()
}