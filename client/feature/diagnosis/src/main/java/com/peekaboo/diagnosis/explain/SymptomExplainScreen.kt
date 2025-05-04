package com.peekaboo.diagnosis.explain

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.DiagnosisSelectPicture
import com.peekaboo.design_system.DiagnosisTitle
import com.peekaboo.design_system.Next
import com.peekaboo.design_system.White3
import com.peekaboo.ui.common.appbar.TopBar
import com.peekaboo.ui.common.button.BottomRectangleBtn
import com.peekaboo.ui.common.content.CourseNumber

@Composable
fun SymptomExplainScreen(
    goToDiagnosisResultPage: () -> Unit,
) {
    SymptomExplainContent(
        onClickNextBtn = { goToDiagnosisResultPage() }
    )
}

@Composable
fun SymptomExplainContent(
    onClickNextBtn: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White3)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            TopBar(
                titleText = DiagnosisTitle,
                isIconValid = true
            )

            CourseNumber(
                currentNumber = 3,
                totalNumber = 3,
                paddingTop = 35,
                paddingStart = 20
            )

            Text(
                text = DiagnosisSelectPicture,
                color = Black1,
                style = BaeBaeTypo.Body1,
                modifier = Modifier
                    .padding(top = 10.dp, start = 20.dp)
            )
        }

        BottomRectangleBtn(
            horizontalPadding = 20,
            btnText = Next,
            isBtnValid = true,
            onClickAction = onClickNextBtn
        )

        Spacer(modifier = Modifier.height(36.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSymptomExplain() {
    SymptomExplainContent()
}