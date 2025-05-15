package com.peekaboo.ui.common.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.peekaboo.design_system.BackToMain
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.DiagnosingCustomizedResult
import com.peekaboo.design_system.DiagnosingResultBtn
import com.peekaboo.design_system.DiagnosingResultEachTitle
import com.peekaboo.design_system.DiagnosingResultRankingEnd
import com.peekaboo.design_system.DiagnosisResultRanking
import com.peekaboo.design_system.DiagnosisResultTitle
import com.peekaboo.design_system.Empty
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
    onClickDiagnosisBtn: () -> Unit = {},
    isDataUpdateSuccess: Boolean = false,
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
                if (isDataUpdateSuccess) {
                    DiagnosisRanking(
                        firstDiseaseModel = firstDiseaseModel,
                        secondDiseaseModel = secondDiseaseModel,
                        thirdDiseaseModel = thirdDiseaseModel
                    )
                } else {
                    DiagnosisRankingShimmer()
                }

                if (isDataUpdateSuccess) {
                    DiagnosingSummaryContent(
                        customDescription = customDescription,
                        firstDiseaseName = firstDiseaseModel.diseaseName
                    )
                } else {
                    DiagnosisSummaryShimmer()
                }

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
                    thirdDiseaseModel = thirdDiseaseModel,
                    isDataUpdateSuccess = isDataUpdateSuccess
                )

                if (isDataUpdateSuccess) {
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
                } else {
                    DiseaseDetailDescriptionShimmer()
                }
            }
        }

        BottomRectangleBtn(
            horizontalPadding = 20,
            btnText = DiagnosingResultBtn,
            isBtnValid = isDataUpdateSuccess,
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
        horizontalArrangement = Arrangement.SpaceAround,
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
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 10.dp)
                .width(80.dp)
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
    isDataUpdateSuccess: Boolean = false,
) {
    Text(
        text = DiagnosingResultEachTitle,
        color = Black1,
        style = BaeBaeTypo.Body1,
        modifier = Modifier
            .padding(top = 20.dp, start = 20.dp)
            .fillMaxWidth()
    )

    if (isDataUpdateSuccess) {
        SelectAreaPictureShapeChip(
            onSelectDiseaseChip = onSelectDisease,
            selectedDisease = selectedDisease,
            firstDiseaseModel = firstDiseaseModel,
            secondDiseaseModel = secondDiseaseModel,
            thirdDiseaseModel = thirdDiseaseModel
        )
    } else {
        SelectAreaChipShimmer()
    }
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
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(8.dp))
            .background(Gray1),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SelectAreaPictureShapeChipItem(
            modifier = Modifier.weight(1f),
            isSelected = (selectedDisease == thirdDiseaseModel.diseaseName),
            onSelectChip = { onSelectDiseaseChip(thirdDiseaseModel.diseaseName) },
            diseaseName = thirdDiseaseModel.diseaseName
        )

        SelectAreaPictureShapeChipItem(
            modifier = Modifier.weight(1f),
            isSelected = (selectedDisease == secondDiseaseModel.diseaseName),
            onSelectChip = { onSelectDiseaseChip(secondDiseaseModel.diseaseName) },
            diseaseName = secondDiseaseModel.diseaseName
        )

        SelectAreaPictureShapeChipItem(
            modifier = Modifier.weight(1f),
            isSelected = (selectedDisease == firstDiseaseModel.diseaseName),
            onSelectChip = { onSelectDiseaseChip(firstDiseaseModel.diseaseName) },
            diseaseName = firstDiseaseModel.diseaseName
        )
    }
}

@Composable
fun SelectAreaPictureShapeChipItem(
    modifier: Modifier,
    isSelected: Boolean = false,
    onSelectChip: () -> Unit,
    diseaseName: String
) {
    Box(
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 4.dp)
//            .weight(1f)
            .fillMaxHeight()
            .clip(RoundedCornerShape(5.dp))
            .background(if (isSelected) White2 else Gray1)
            .clickable(
                onClick = onSelectChip
            )
    ) {
        Text(
            text = diseaseName,
            color = if (isSelected) Black1 else Gray3,
            style = BaeBaeTypo.Body3,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 10.dp)
        )
    }
}

@Composable
fun DiagnosisRankingShimmer() {
    LoadingShimmerEffect { shimmer ->
        Row(
            modifier = Modifier
                .padding(horizontal = 56.dp, vertical = 25.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom
        ) {
            DiagnosisRankingShimmerItem(
                height = 60,
                brush = shimmer
            )

            DiagnosisRankingShimmerItem(
                height = 90,
                brush = shimmer
            )

            DiagnosisRankingShimmerItem(
                height = 120,
                brush = shimmer
            )
        }
    }
}

@Composable
internal fun DiagnosisRankingShimmerItem(
    height: Int,
    brush: Brush,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(width = 52.dp, height = height.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(brush)
        )

        Text(
            text = Empty,
            color = Color.Transparent,
            style = BaeBaeTypo.Caption4,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 10.dp)
                .width(80.dp)
        )
        Text(
            text = Empty,
            color = Color.Transparent,
            style = BaeBaeTypo.Caption4,
        )
    }
}

@Composable
fun DiagnosisSummaryShimmer() {
    LoadingShimmerEffect { shimmer ->
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(shimmer)
        ) {
            Text(
                text = DiagnosisResultRanking + DiagnosingResultRankingEnd,
                color = Color.Transparent,
                textAlign = TextAlign.Center,
                style = BaeBaeTypo.Body3,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 25.dp)
            )
        }
    }

    LoadingShimmerMainEffect { shimmer ->
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(shimmer)
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 12.dp, start = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = Empty,
                    color = Color.Transparent,
                    style = BaeBaeTypo.Body3,
                    modifier = Modifier
                        .padding(start = 5.dp)
                )
            }

            Text(
                text = Empty,
                color = Color.Transparent,
                style = BaeBaeTypo.Body3,
                modifier = Modifier
                    .padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun SelectAreaChipShimmer() {
    LoadingShimmerEffect { shimmer ->
        Box(
            modifier = Modifier
                .padding(top = 35.dp, start = 23.dp, end = 23.dp)
                .fillMaxWidth()
                .height(44.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(shimmer)
        )
    }
}

@Composable
fun DiseaseDetailDescriptionShimmer() {

    LoadingShimmerEffect { shimmer ->
        Spacer(modifier = Modifier.height(35.dp))

        Box(
            modifier = Modifier
                .padding(start = 20.dp)
                .size(width = 40.dp, height = 20.dp)
                .background(shimmer)
        )

        Box(
            modifier = Modifier
                .padding(top = 10.dp, start = 23.dp, end = 23.dp)
                .fillMaxWidth()
                .height(35.dp)
                .background(shimmer)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDiagnosisContent() {
    DiagnosisContent()
}