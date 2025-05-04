package com.peekaboo.diagnosis.selectarea

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.DiagnosisSelectArea
import com.peekaboo.design_system.DiagnosisTitle
import com.peekaboo.design_system.Next
import com.peekaboo.design_system.White3
import com.peekaboo.domain.entity.request.DiagnosisModel
import com.peekaboo.ui.common.appbar.TopBar
import com.peekaboo.ui.common.button.BottomRectangleBtn
import com.peekaboo.ui.common.content.CourseNumber

@Composable
fun SelectAreaScreen(
    goToPicturePage: (DiagnosisModel) -> Unit,
) {

    val viewModel: SelectAreaViewModel = hiltViewModel()
    val uiState: SelectAreaPageState by viewModel.uiState.collectAsStateWithLifecycle()

    SelectAreaContent(
        onClickNextBtn = { goToPicturePage(viewModel.updateDiagnosisContent()) }
    )
}

@Composable
fun SelectAreaContent(
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
                currentNumber = 1,
                totalNumber = 3,
                paddingTop = 35,
                paddingStart = 20
            )

            Text(
                text = DiagnosisSelectArea,
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
fun PreviewSelectArea() {
    SelectAreaContent()
}