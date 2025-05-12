package com.peekaboo.diagnosis

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.peekaboo.design_system.BackToMain
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.DiagnosingCustomizedResult
import com.peekaboo.design_system.DiagnosingResultBtn
import com.peekaboo.design_system.DiagnosingResultEachTitle
import com.peekaboo.design_system.DiagnosingResultRankingEnd
import com.peekaboo.design_system.DiagnosisResultRanking
import com.peekaboo.design_system.DiagnosisResultTitle
import com.peekaboo.design_system.Gray1
import com.peekaboo.design_system.Gray2
import com.peekaboo.design_system.Gray3
import com.peekaboo.design_system.Main2
import com.peekaboo.design_system.Main3
import com.peekaboo.design_system.Percent
import com.peekaboo.design_system.R
import com.peekaboo.design_system.White2
import com.peekaboo.design_system.White3
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryDetailModel
import com.peekaboo.ui.common.appbar.TopBar
import com.peekaboo.ui.common.button.BottomRectangleBtn
import com.peekaboo.ui.common.content.DiseaseDetail
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun DiagnosisScreen(
    goBackToMain: () -> Unit,
    selectedDiagnosisHistoryId: SharedFlow<Int>,
    showLanguageBottomSheet: () -> Unit,
    selectedLanguage: SharedFlow<String>
) {
    val viewModel: DiagnosisViewModel = hiltViewModel()
    val uiState: DiagnosisPageState by viewModel.uiState.collectAsStateWithLifecycle()

    val interactionSource = remember { MutableInteractionSource() }
    val context = LocalContext.current

    LaunchedEffect(selectedDiagnosisHistoryId) {
        selectedDiagnosisHistoryId.collect {
            viewModel.setDiagnosisResult(it)
        }
    }

    LaunchedEffect(selectedLanguage) {
        selectedLanguage.collect {
            viewModel.setSelectedLanguage(it, context)
        }
    }

    DiagnosisContent(
        interactionSource = interactionSource,
        onSelectDisease = { viewModel.setSelectedDisease(it) },
        selectedDisease = uiState.selectedDisease,
        onClickBackToMain = { goBackToMain() },
        diseaseTotal = uiState.diseaseTotal,
        customDescription = uiState.customDescription,
        firstDiseaseModel = uiState.firstDiseaseModel,
        secondDiseaseModel = uiState.secondDiseaseModel,
        thirdDiseaseModel = uiState.thirdDiseaseModel,
        onClickDiagnosisBtn = {
            showLanguageBottomSheet()
        }
    )
}

@Composable
fun DiagnosisContent(
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    onSelectDisease: (String) -> Unit = {},
    selectedDisease: String = "",
    onClickBackToMain: () -> Unit = {},
    customDescription: String = "",
    diseaseTotal: List<DiagnosisHistoryDetailModel.DiseaseDetailItem> = emptyList(),
    firstDiseaseModel: DiagnosisHistoryDetailModel.DiseaseDetailItem = DiagnosisHistoryDetailModel.DiseaseDetailItem(),
    secondDiseaseModel: DiagnosisHistoryDetailModel.DiseaseDetailItem = DiagnosisHistoryDetailModel.DiseaseDetailItem(),
    thirdDiseaseModel: DiagnosisHistoryDetailModel.DiseaseDetailItem = DiagnosisHistoryDetailModel.DiseaseDetailItem(),
    onClickDiagnosisBtn: () -> Unit = {}
) {
    val diseaseModel =
        diseaseTotal.firstOrNull { it.diseaseName == selectedDisease } ?: firstDiseaseModel
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White3)
    ) {
        TopBar(
            titleText = DiagnosisResultTitle
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) {
            item {
                DiagnosisRanking(
                    firstDiseaseModel = firstDiseaseModel,
                    secondDiseaseModel = secondDiseaseModel,
                    thirdDiseaseModel = thirdDiseaseModel
                )

                DiagnosingSummaryContent(
                    customDescription = customDescription,
                    firstDiseaseName = firstDiseaseModel.diseaseName
                )

                Divider(
                    modifier = Modifier
                        .padding(top = 18.dp)
                        .border(3.dp, color = Gray1)
                )

                DiagnosingEachResult(
                    selectedDisease = selectedDisease,
                    onSelectDisease = onSelectDisease,
                    firstDiseaseModel = firstDiseaseModel,
                    secondDiseaseModel = secondDiseaseModel,
                    thirdDiseaseModel = thirdDiseaseModel
                )

                DiseaseDetail(
                    isDetailDescriptionValid = true,
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
            btnText = DiagnosingResultBtn,
            isBtnValid = true,
            onClickAction = onClickDiagnosisBtn
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
                    interactionSource = interactionSource,
                    indication = null
                )
        )
    }
}

@Composable
fun DiagnosisRanking(
    firstDiseaseModel: DiagnosisHistoryDetailModel.DiseaseDetailItem,
    secondDiseaseModel: DiagnosisHistoryDetailModel.DiseaseDetailItem,
    thirdDiseaseModel: DiagnosisHistoryDetailModel.DiseaseDetailItem,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 56.dp, vertical = 25.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        DiagnosingRankingItem(
            diseaseName = thirdDiseaseModel.diseaseName,
            percent = thirdDiseaseModel.percent,
            height = 60,
        )

        DiagnosingRankingItem(
            diseaseName = secondDiseaseModel.diseaseName,
            percent = secondDiseaseModel.percent,
            height = 90,
        )

        DiagnosingRankingItem(
            diseaseName = firstDiseaseModel.diseaseName,
            percent = firstDiseaseModel.percent,
            height = 120,
            isRankingFirst = true
        )
    }
}

@Composable
fun DiagnosingRankingItem(
    diseaseName: String,
    percent: Int,
    height: Int,
    isRankingFirst: Boolean = false,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(width = 52.dp, height = height.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(if (isRankingFirst) Main2 else Gray2)
        )

        Text(
            text = diseaseName,
            color = if (isRankingFirst) Main2 else Gray3,
            style = BaeBaeTypo.Caption4,
            modifier = Modifier
                .padding(top = 10.dp)
        )
        Text(
            text = String.format(Percent, percent),
            color = if (isRankingFirst) Main2 else Gray3,
            style = BaeBaeTypo.Caption4,
        )
    }
}

@Composable
fun DiagnosingSummaryContent(
    customDescription: String,
    firstDiseaseName: String,
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(Gray1)
    ) {
        Text(
            text = buildAnnotatedString {
                append(DiagnosisResultRanking)
                withStyle(style = SpanStyle(color = Main2)) {
                    append(firstDiseaseName)
                }
                append(DiagnosingResultRankingEnd)
            },
            color = Black1,
            textAlign = TextAlign.Center,
            style = BaeBaeTypo.Body3,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 25.dp)
        )
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 12.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(Main3)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 12.dp, start = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_light_bulb),
                contentDescription = "customized",
                modifier = Modifier
                    .size(24.dp)
            )

            Text(
                text = DiagnosingCustomizedResult,
                color = White2,
                style = BaeBaeTypo.Body3,
                modifier = Modifier
                    .padding(start = 5.dp)
            )
        }

        Text(
            text = customDescription,
            color = White2,
            style = BaeBaeTypo.Body3,
            modifier = Modifier
                .padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun DiagnosingEachResult(
    onSelectDisease: (String) -> Unit = {},
    selectedDisease: String = "",
    firstDiseaseModel: DiagnosisHistoryDetailModel.DiseaseDetailItem,
    secondDiseaseModel: DiagnosisHistoryDetailModel.DiseaseDetailItem,
    thirdDiseaseModel: DiagnosisHistoryDetailModel.DiseaseDetailItem,
) {
    Text(
        text = DiagnosingResultEachTitle,
        color = Black1,
        style = BaeBaeTypo.Body1,
        modifier = Modifier
            .padding(top = 20.dp, start = 20.dp)
            .fillMaxWidth()
    )

    SelectAreaPictureShapeChip(
        onSelectDiseaseChip = onSelectDisease,
        selectedDisease = selectedDisease,
        firstDiseaseModel = firstDiseaseModel,
        secondDiseaseModel = secondDiseaseModel,
        thirdDiseaseModel = thirdDiseaseModel
    )
}

@Composable
fun SelectAreaPictureShapeChip(
    onSelectDiseaseChip: (String) -> Unit,
    selectedDisease: String,
    firstDiseaseModel: DiagnosisHistoryDetailModel.DiseaseDetailItem,
    secondDiseaseModel: DiagnosisHistoryDetailModel.DiseaseDetailItem,
    thirdDiseaseModel: DiagnosisHistoryDetailModel.DiseaseDetailItem,
) {
    Row(
        modifier = Modifier
            .padding(top = 35.dp, start = 23.dp, end = 23.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Gray1)
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 4.dp)
                .weight(1f)
                .clip(RoundedCornerShape(5.dp))
                .background(if (selectedDisease == thirdDiseaseModel.diseaseName) White2 else Gray1)
                .clickable(
                    onClick = { onSelectDiseaseChip(thirdDiseaseModel.diseaseName) }
                )
        ) {
            Text(
                text = thirdDiseaseModel.diseaseName,
                color = if (selectedDisease == thirdDiseaseModel.diseaseName) Black1 else Gray3,
                style = BaeBaeTypo.Body3,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 10.dp)
            )
        }

        Box(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .clip(RoundedCornerShape(5.dp))
                .weight(1f)
                .background(if (selectedDisease == secondDiseaseModel.diseaseName) White2 else Gray1)
                .clickable(
                    onClick = { onSelectDiseaseChip(secondDiseaseModel.diseaseName) }
                )
        ) {
            Text(
                text = secondDiseaseModel.diseaseName,
                color = if (selectedDisease == secondDiseaseModel.diseaseName) Black1 else Gray3,
                style = BaeBaeTypo.Body3,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 10.dp)
            )
        }

        Box(
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 4.dp)
                .weight(1f)
                .clip(RoundedCornerShape(5.dp))
                .background(if (selectedDisease == firstDiseaseModel.diseaseName) White2 else Gray1)
                .clickable(
                    onClick = { onSelectDiseaseChip(firstDiseaseModel.diseaseName) }
                )
        ) {
            Text(
                text = firstDiseaseModel.diseaseName,
                color = if (selectedDisease == firstDiseaseModel.diseaseName) Black1 else Gray3,
                style = BaeBaeTypo.Body3,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 10.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDiagnosis() {
    DiagnosisContent()
}