package com.peekaboo.diagnosis

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.DiagnosingCustomizedResult
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
import com.peekaboo.ui.common.appbar.TopBar

@Composable
fun DiagnosisScreen() {
    val viewModel: DiagnosisViewModel = hiltViewModel()
    val uiState: DiagnosisPageState by viewModel.uiState.collectAsStateWithLifecycle()

    DiagnosisContent(
        onSelectDisease = { viewModel.setSelectedDisease(it) },
        selectedDisease = uiState.selectedDisease
    )
}

@Composable
fun DiagnosisContent(
    onSelectDisease: (String) -> Unit = {},
    selectedDisease: String = ""
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White3)
    ) {
        TopBar(
            titleText = DiagnosisResultTitle
        )

        DiagnosisRanking()

        DiagnosingSummaryContent()

        Divider(modifier = Modifier
            .padding(top = 18.dp)
            .border(3.dp, color = Gray1))

        DiagnosingEachResult(
            selectedDisease = selectedDisease,
            onSelectDisease = onSelectDisease
        )
    }
}

@Composable
fun DiagnosisRanking() {
    Row(
        modifier = Modifier
            .padding(horizontal = 56.dp, vertical = 25.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        DiagnosingRankingItem(
            diseaseName = "접촉성 피부염",
            percent = 20,
            height = 60,
        )

        DiagnosingRankingItem(
            diseaseName = "수두",
            percent = 60,
            height = 90,
        )

        DiagnosingRankingItem(
            diseaseName = "땀띠",
            percent = 80,
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
                .background(Gray2)
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
fun DiagnosingSummaryContent() {
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
                    append("땀띠")
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
            text = "맞춤형 정보 어쩌고 저쩌고 ",
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
    selectedDisease: String = ""
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
        selectedDisease = selectedDisease
    )
}

@Composable
fun SelectAreaPictureShapeChip(
    onSelectDiseaseChip: (String) -> Unit,
    selectedDisease: String = "접촉성 피부염",
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
                .background(if (selectedDisease == "접촉성 피부염") White2 else Gray1)
                .clickable(
                    onClick = { onSelectDiseaseChip("접촉성 피부염") }
                )
        ) {
            Text(
                text = "접촉성 피부염",
                color = if (selectedDisease == "접촉성 피부염") Black1 else Gray3,
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
                .background(if (selectedDisease == "수두") White2 else Gray1)
                .clickable(
                    onClick = { onSelectDiseaseChip("수두") }
                )
        ) {
            Text(
                text = "수두",
                color = if (selectedDisease == "수두") Black1 else Gray3,
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
                .background(if (selectedDisease == "땀띠") White2 else Gray1)
                .clickable(
                    onClick = { onSelectDiseaseChip("땀띠") }
                )
        ) {
            Text(
                text = "땀띠",
                color = if (selectedDisease == "땀띠") Black1 else Gray3,
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