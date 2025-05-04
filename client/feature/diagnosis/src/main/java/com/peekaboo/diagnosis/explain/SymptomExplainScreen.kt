package com.peekaboo.diagnosis.explain

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.DiagnosisExplainHint
import com.peekaboo.design_system.DiagnosisSelectPicture
import com.peekaboo.design_system.DiagnosisTitle
import com.peekaboo.design_system.Finish
import com.peekaboo.design_system.White3
import com.peekaboo.domain.entity.request.DiagnosisModel
import com.peekaboo.ui.common.appbar.TopBar
import com.peekaboo.ui.common.button.BottomRectangleBtn
import com.peekaboo.ui.common.content.CourseNumber
import com.peekaboo.ui.common.item.TextFieldBox
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun SymptomExplainScreen(
    diagnosisModel: SharedFlow<DiagnosisModel>,
    goToDiagnosisResultPage: (DiagnosisModel) -> Unit,
) {
    val viewModel: SymptomExplainViewModel = hiltViewModel()
    val uiState: SymptomExplainPageState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(diagnosisModel) {
        diagnosisModel.collect {
            viewModel.setDiagnosisContent(it)
        }
    }

    SymptomExplainContent(
        onClickNextBtn = { goToDiagnosisResultPage(viewModel.updateDiagnosisContent()) },
        explainInput = uiState.explainInput,
        onExplainValueChange = { viewModel.onExplainValueChange(it) }
    )
}

@Composable
fun SymptomExplainContent(
    onClickNextBtn: () -> Unit = {},
    explainInput: String = "",
    onExplainValueChange: (String) -> Unit = {},
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

            Spacer(modifier = Modifier.height(87.dp))

            TextFieldBox(
                textInput = explainInput,
                onValueChange = onExplainValueChange,
                horizontalPadding = 25,
                hintText = DiagnosisExplainHint,
                height = 260,
                textMaxLength = 500
            )
        }

        BottomRectangleBtn(
            horizontalPadding = 20,
            btnText = Finish,
            isBtnValid = explainInput.isNotEmpty(),
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