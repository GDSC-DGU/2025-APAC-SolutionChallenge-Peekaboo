package com.peekaboo.diagnosisquick.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.peekaboo.design_system.BackToMain
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Gray2
import com.peekaboo.design_system.Gray3
import com.peekaboo.design_system.Main3
import com.peekaboo.design_system.QuickDiagnosisTitle
import com.peekaboo.design_system.QuickDiseaseAdditionalBtn
import com.peekaboo.design_system.White2
import com.peekaboo.design_system.White3
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisConstModel
import com.peekaboo.ui.common.appbar.TopBar
import com.peekaboo.ui.common.button.BottomRectangleBtn
import com.peekaboo.ui.common.content.DiseaseDetail
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun DetailQuickScreen(
    diagnosisConstId: SharedFlow<Int>,
    goBackToMain: () -> Unit,
    goToDiagnosisPage: () -> Unit,
) {
    val viewModel: DetailQuickViewModel = hiltViewModel()
    val uiState: DetailQuickPageState by viewModel.uiState.collectAsStateWithLifecycle()

    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(diagnosisConstId) {
        diagnosisConstId.collect {
            viewModel.initSetDiagnosisModel(it)
        }
    }

    DetailQuickContent(
        interactionSource = interactionSource,
        onClickBackToMain = { goBackToMain() },
        onClickAdditionalDiagnosis = { goToDiagnosisPage() },
        diseaseModel = uiState.diagnosisModel
    )
}

@Composable
fun DetailQuickContent(
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    onClickBackToMain: () -> Unit = {},
    onClickAdditionalDiagnosis: () -> Unit = {},
    diseaseModel: DiagnosisConstModel = DiagnosisConstModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White3)
    ) {
        TopBar(
            titleText = QuickDiagnosisTitle,
            isIconValid = true
        )

        DetailDescriptionBanner(
            diseaseName = diseaseModel.diseaseName,
            description = diseaseModel.description
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) {
            item {
                DiseaseDetail(
                    diseaseName = diseaseModel.diseaseName,
                    description = diseaseModel.description,
                    rating = diseaseModel.rating,
                    symptoms = diseaseModel.symptoms,
                    type = diseaseModel.type,
                    site = diseaseModel.site,
                    reason = diseaseModel.reason,
                    drugs = diseaseModel.drugs,
                    mild = diseaseModel.mild,
                    severe = diseaseModel.severe,
                    preventive = diseaseModel.preventive,
                    caution = diseaseModel.caution
                )
            }
        }

        BottomRectangleBtn(
            horizontalPadding = 20,
            btnText = QuickDiseaseAdditionalBtn,
            isBtnValid = true,
            onClickAction = onClickAdditionalDiagnosis
        )

        Text(
            text = BackToMain,
            color = Gray3,
            style = BaeBaeTypo.Body3.copy(
                textDecoration = TextDecoration.Underline
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp, bottom = 27.dp)
                .clickable(
                    onClick = onClickBackToMain,
                    indication = null,
                    interactionSource = interactionSource
                )
        )
    }
}

@Composable
fun DetailDescriptionBanner(
    diseaseName: String,
    description: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 3.dp, bottomEnd = 3.dp))
            .background(Main3)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 40.dp, end = 22.dp, top = 40.dp)
                .weight(1f)
        ) {
            Text(
                text = diseaseName,
                color = White2,
                style = BaeBaeTypo.Head1,
            )

            Text(
                text = description,
                color = Gray2,
                style = BaeBaeTypo.Caption1,
                modifier = Modifier
                    .padding(top = 15.dp)
            )
        }

        Box(
            modifier = Modifier
                .padding(vertical = 39.dp)
                .padding(end = 30.dp)
                .size(width = 85.dp, height = 122.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Gray2),
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDetailQuick() {
    DetailQuickContent()
}