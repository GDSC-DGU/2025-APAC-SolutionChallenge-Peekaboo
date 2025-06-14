package com.peekaboo.diagnosishistory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.peekaboo.design_system.And
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.CustomizedInfo
import com.peekaboo.design_system.DiagnosingBtn
import com.peekaboo.design_system.DiagnosisEmptyTitle
import com.peekaboo.design_system.DiagnosisHistoryRanking
import com.peekaboo.design_system.DiagnosisHistoryTitle
import com.peekaboo.design_system.Gray1
import com.peekaboo.design_system.Gray3
import com.peekaboo.design_system.Main2
import com.peekaboo.design_system.R
import com.peekaboo.design_system.White2
import com.peekaboo.design_system.White3
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryResponseModel
import com.peekaboo.ui.common.appbar.TopBar
import com.peekaboo.ui.common.button.BottomRectangleBtn
import com.peekaboo.ui.common.content.LoadingShimmerEffect

@Composable
fun DiagnosisHistoryScreen(
    goToDiagnosisPage: () -> Unit,
    goToDiagnosisResultPage: (Int) -> Unit,
    onClickBackBtn: () -> Unit,
) {

    val viewModel: DiagnosisHistoryViewModel = hiltViewModel()
    val uiState: DiagnosisHistoryPageState by viewModel.uiState.collectAsStateWithLifecycle()

    DiagnosisHistoryContent(
        onClickDiagnosingBtn = { goToDiagnosisPage() },
        onClickDiagnosisDetail = { id -> goToDiagnosisResultPage(id) },
        diagnosisHistoryList = uiState.diagnosisHistoryList,
        onClickBackBtn = onClickBackBtn,
        isDataUpdated = uiState.isDataUpdated
    )
}

@Composable
fun DiagnosisHistoryContent(
    onClickDiagnosingBtn: () -> Unit = {},
    onClickDiagnosisDetail: (Int) -> Unit = {},
    diagnosisHistoryList: List<DiagnosisHistoryResponseModel.DiagnosisHistoryItem> = emptyList(),
    onClickBackBtn: () -> Unit = {},
    isDataUpdated: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White3)
    ) {
        TopBar(
            titleText = DiagnosisHistoryTitle,
            isIconValid = true,
            onClickIcon = onClickBackBtn
        )

        if (isDataUpdated) {
            if (diagnosisHistoryList.isEmpty()) {
                DiagnosisHistoryEmptyContent(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClickDiagnosingBtn = onClickDiagnosingBtn
                )
            } else {
                DiagnosisHistoryDataContent(
                    diagnosisHistoryList = diagnosisHistoryList,
                    onClickDiagnosisDetail = onClickDiagnosisDetail
                )
            }
        } else {
            DiagnosisHistoryShimmer()
        }
    }
}

@Composable
fun DiagnosisHistoryDataContent(
    diagnosisHistoryList: List<DiagnosisHistoryResponseModel.DiagnosisHistoryItem>,
    onClickDiagnosisDetail: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .padding(vertical = 23.dp),
        verticalArrangement = Arrangement.spacedBy(35.dp)
    ) {
        items(diagnosisHistoryList) { historyData ->
            DiagnosisHistoryDataItem(
                historyData = historyData,
                onClickAction = { onClickDiagnosisDetail(historyData.diagnosisId) }
            )
        }
    }
}

@Composable
fun DiagnosisHistoryShimmer() {
    LoadingShimmerEffect { shimmer ->
        LazyColumn(
            modifier = Modifier
                .padding(vertical = 23.dp),
            verticalArrangement = Arrangement.spacedBy(35.dp)
        ) {
            items(2) {
                DiagnosisHistoryShimmerItem(brush = shimmer)
            }
        }
    }
}

@Composable
fun DiagnosisHistoryShimmerItem(
    brush: Brush,
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 23.dp)
            .size(15.dp)
            .background(Main2, shape = CircleShape)
    )

    Box(
        modifier = Modifier
            .padding(top = 8.dp)
            .padding(horizontal = 23.dp)
            .fillMaxWidth()
            .height(95.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(brush)
            .border(1.dp, color = Gray1, RoundedCornerShape(6.dp))
    )
}

@Composable
fun DiagnosisHistoryDataItem(
    historyData: DiagnosisHistoryResponseModel.DiagnosisHistoryItem,
    onClickAction: () -> Unit,
) {
    val rankingFirstDisease =
        DiagnosisHistoryCheckRanking(data = historyData.diseaseList, ranking = 1)
    val rankingSecondName =
        DiagnosisHistoryCheckRanking(data = historyData.diseaseList, ranking = 2)
    val rankingThirdName = DiagnosisHistoryCheckRanking(data = historyData.diseaseList, ranking = 3)

    Row(
        modifier = Modifier
            .padding(horizontal = 23.dp)
    ) {
        Box(
            modifier = Modifier
                .size(15.dp)
                .background(Main2, shape = CircleShape)
        )

        Text(
            text = historyData.createAt,
            color = Black1,
            style = BaeBaeTypo.Caption4,
            modifier = Modifier
                .padding(start = 10.dp)
        )
    }

    Column(
        modifier = Modifier
            .padding(top = 8.dp)
            .padding(horizontal = 23.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(White2)
            .border(1.dp, color = Gray1, RoundedCornerShape(6.dp))
            .clickable(
                onClick = onClickAction
            )
    ) {
        Text(
            text = rankingFirstDisease,
            color = Main2,
            style = BaeBaeTypo.Body3,
            modifier = Modifier
                .padding(top = 12.dp, start = 15.dp)
        )

        Text(
            text = buildAnnotatedString {
                append(CustomizedInfo)
                withStyle(style = SpanStyle(color = Gray3)) {
                    append(historyData.customDescription)
                }
            },
            color = Black1,
            style = BaeBaeTypo.Caption2,
            modifier = Modifier
                .padding(top = 6.dp, start = 15.dp)
        )

        Text(
            text = buildAnnotatedString {
                append(DiagnosisHistoryRanking)
                withStyle(style = SpanStyle(color = Main2)) {
                    append(rankingSecondName)
                }

                append(And)

                withStyle(style = SpanStyle(color = Main2)) {
                    append(rankingThirdName)
                }
            },
            color = Gray3,
            style = BaeBaeTypo.Caption2,
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 15.dp)
        )
    }
}

fun DiagnosisHistoryCheckRanking(
    data: List<DiagnosisHistoryResponseModel.DiagnosisHistoryItem.DiseaseItem>,
    ranking: Int,
): String {
    val rankingDisase: String = data.firstOrNull { it.ranking == ranking }?.diseaseName ?: ""
    return rankingDisase
}

@Composable
fun DiagnosisHistoryEmptyContent(
    modifier: Modifier,
    onClickDiagnosingBtn: () -> Unit,
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