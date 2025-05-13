package com.peekaboo.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.peekaboo.design_system.BaeBae
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.BannerIndicator
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.CourseNumberFormat
import com.peekaboo.design_system.DiagnosingBtn
import com.peekaboo.design_system.DiagnosingTypeFullView
import com.peekaboo.design_system.DiagnosingTypeListSemiTitle
import com.peekaboo.design_system.DiagnosingTypeListTitle
import com.peekaboo.design_system.DiagnosisHistorySemiTitle
import com.peekaboo.design_system.DiagnosisHistoryTitle
import com.peekaboo.design_system.Gray1
import com.peekaboo.design_system.Gray2
import com.peekaboo.design_system.Gray3
import com.peekaboo.design_system.HomeDiagnosingBoxSemiTitle
import com.peekaboo.design_system.HomeDiagnosingBoxTitle
import com.peekaboo.design_system.HomeSemiTitle
import com.peekaboo.design_system.Main2
import com.peekaboo.design_system.Main3
import com.peekaboo.design_system.Question
import com.peekaboo.design_system.R
import com.peekaboo.design_system.SkinCondition
import com.peekaboo.design_system.White2
import com.peekaboo.design_system.White3
import com.peekaboo.domain.entity.response.DiseaseBannerItem
import com.peekaboo.ui.common.button.BottomRectangleBtn
import com.peekaboo.ui.common.type.DiseaseType

@Composable
fun HomeScreen(
    goToDiagnosisSelectAreaPage: () -> Unit,
    goToDiagnosisHistoryPage: () -> Unit,
    goToDiagnosisQuickPage: () -> Unit,
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState: HomePageState by viewModel.uiState.collectAsStateWithLifecycle()

    val interactionSource = remember { MutableInteractionSource() }

    HomeContent(
        interactionSource = interactionSource,
        onClickDiagnosisBtn = { goToDiagnosisSelectAreaPage() },
        onClickDiagnosisHistoryBox = { goToDiagnosisHistoryPage() },
        onClickDiseaseListFullView = { goToDiagnosisQuickPage() },
        diseaseBannerList = uiState.diseaseBannerList
    )
}

@Composable
fun HomeContent(
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    onClickDiagnosisBtn: () -> Unit = {},
    onClickDiagnosisHistoryBox: () -> Unit = {},
    onClickDiseaseListFullView: () -> Unit = {},
    diseaseBannerList: List<DiseaseBannerItem> = emptyList(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White3)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 15.dp, horizontal = 20.dp)
        ) {
            Text(
                text = BaeBae,
                color = Black1,
                style = BaeBaeTypo.Head1,
                modifier = Modifier
                    .weight(1f)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_setting),
                contentDescription = "setting",
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            HomeDiseaseBanner(
                bannerDiseaseList = diseaseBannerList
            )
        }

        Text(
            text = buildAnnotatedString {
                append(HomeSemiTitle)
                withStyle(style = SpanStyle(color = Main2)) {
                    append(BaeBae)
                }
            },
            color = Black1,
            style = BaeBaeTypo.Body1,
            modifier = Modifier
                .padding(top = 30.dp, start = 20.dp)
        )

        HomeDiagnosingBox(
            onClickDiagnosisBtn = onClickDiagnosisBtn
        )

        HomeDiagnosisHistory(
            interactionSource = interactionSource,
            onClickDiagnosisHistoryBox = onClickDiagnosisHistoryBox
        )

        HomeDiseaseTypeList(
            interactionSource = interactionSource,
            onClickDiseaseListFullView = onClickDiseaseListFullView
        )
    }
}

@Composable
fun HomeDiseaseBanner(
    bannerDiseaseList: List<DiseaseBannerItem>,
) {
    val pagerState = rememberPagerState(pageCount = { bannerDiseaseList.size })

    HorizontalPager(
        state = pagerState,
    ) { page ->
        HomeDiseaseBannerItem(
            diseaseItem = bannerDiseaseList[page],
            page = page + 1,
            size = bannerDiseaseList.size
        )
    }
}

@Composable
fun HomeDiseaseBannerItem(
    diseaseItem: DiseaseBannerItem,
    page: Int,
    size: Int,
) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    Box(
        modifier = Modifier
            .width(screenWidthDp.dp)
            .wrapContentHeight()
            .clip(RoundedCornerShape(bottomStart = 5.dp, bottomEnd = 5.dp))
            .background(Main3),
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 50.dp, top = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = diseaseItem.name,
                        color = White2,
                        style = BaeBaeTypo.Head1,
                    )

                    Text(
                        text = diseaseItem.location,
                        color = Gray2,
                        style = BaeBaeTypo.Body1,
                        modifier = Modifier
                            .padding(start = 15.dp)
                    )
                }

                Text(
                    text = diseaseItem.description,
                    color = Gray2,
                    style = BaeBaeTypo.Caption1,
                    modifier = Modifier
                        .padding(top = 20.dp)
                )
            }

            Image(
                painter = painterResource(id = R.drawable.ic_home_banner),
                contentDescription = "banner",
                modifier = Modifier
                    .padding(end = 20.dp)
//                    .align(Alignment.TopEnd)
                    .size(100.dp)
                    .clip(RoundedCornerShape(3.dp)),
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 14.dp, bottom = 11.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(BannerIndicator)
        ) {
            Text(
                text = String.format(CourseNumberFormat, page, size),
                color = Gray3,
                style = BaeBaeTypo.Caption3,
                modifier = Modifier
                    .padding(vertical = 2.dp, horizontal = 5.dp)
            )
        }
    }
}

@Composable
fun HomeDiagnosingBox(
    onClickDiagnosisBtn: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(vertical = 15.dp, horizontal = 20.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(White2)
            .border(1.dp, color = Gray1, RoundedCornerShape(6.dp))
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = buildAnnotatedString {
                append(HomeDiagnosingBoxTitle)
                withStyle(style = SpanStyle(color = Main2)) {
                    append(SkinCondition)
                }
                append(Question)
            },
            color = Black1,
            textAlign = TextAlign.Center,
            style = BaeBaeTypo.Body3,
            modifier = Modifier
                .padding(top = 27.dp)
        )

        Text(
            text = HomeDiagnosingBoxSemiTitle,
            color = Black1,
            style = BaeBaeTypo.Caption2,
            modifier = Modifier
                .padding(top = 10.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        BottomRectangleBtn(
            horizontalPadding = 45,
            btnText = DiagnosingBtn,
            isBtnValid = true,
            onClickAction = onClickDiagnosisBtn
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun HomeDiagnosisHistory(
    interactionSource: MutableInteractionSource,
    onClickDiagnosisHistoryBox: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(White2)
            .border(1.dp, color = Gray1, RoundedCornerShape(6.dp))
            .fillMaxWidth()
            .clickable(
                onClick = onClickDiagnosisHistoryBox,
                interactionSource = interactionSource,
                indication = null
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_diagnosis_history),
            contentDescription = "diagnosis history",
            modifier = Modifier
                .padding(vertical = 17.dp)
                .padding(start = 50.dp)
                .size(30.dp)
        )

        Column(
            modifier = Modifier
                .padding(vertical = 17.dp)
                .padding(start = 35.dp)
                .weight(1f)
        ) {
            Text(
                text = DiagnosisHistoryTitle,
                color = Black1,
                style = BaeBaeTypo.Caption1,
            )

            Text(
                text = DiagnosisHistorySemiTitle,
                color = Gray3,
                style = BaeBaeTypo.Caption2,
                modifier = Modifier
                    .padding(top = 4.dp)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_arrow_next),
            contentDescription = "next",
        )

        Spacer(modifier = Modifier.width(12.dp))
    }
}

@Composable
fun HomeDiseaseTypeList(
    interactionSource: MutableInteractionSource,
    onClickDiseaseListFullView: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(vertical = 15.dp, horizontal = 20.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(White2)
            .border(1.dp, color = Gray1, RoundedCornerShape(6.dp))
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Row(
            modifier = Modifier
                .padding(top = 12.dp)
                .padding(start = 18.dp, end = 13.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = DiagnosingTypeListTitle,
                color = Black1,
                style = BaeBaeTypo.Body3,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = DiagnosingTypeFullView,
                color = Gray3,
                style = BaeBaeTypo.Caption2,
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 5.dp)
                    .clickable(
                        onClick = onClickDiseaseListFullView,
                        indication = null,
                        interactionSource = interactionSource
                    )
            )
        }

        Text(
            text = DiagnosingTypeListSemiTitle,
            color = Gray3,
            style = BaeBaeTypo.Caption2,
            modifier = Modifier
                .padding(top = 10.dp, start = 18.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        DiseaseTypeList()

        Spacer(modifier = Modifier.height(33.dp))
    }
}

@Composable
fun DiseaseTypeList() {
    LazyRow(
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(18.dp),
        contentPadding = PaddingValues(horizontal = 18.dp)
    ) {
        items(DiseaseType.entries) { diseaseType ->
            DiseaseTypeItem(
                diseaseImg = diseaseType.diseaseImg,
                diseaseName = diseaseType.diseaseNameEng
            )
        }
    }
}

@Composable
fun DiseaseTypeItem(
    diseaseImg: Int,
    diseaseName: String,
) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = diseaseImg),
            contentDescription = "disease",
            modifier = Modifier
                .size(width = 35.dp, height = 40.dp)
                .clip(RoundedCornerShape(25.dp))
        )

        Text(
            text = diseaseName,
            color = Black1,
            style = BaeBaeTypo.Caption4,
            modifier = Modifier
                .padding(top = 5.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHome() {
    HomeContent()
}