package com.peekaboo.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.peekaboo.design_system.BaeBae
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
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
import com.peekaboo.design_system.HomeDiagnosingBtn
import com.peekaboo.design_system.HomeSemiTitle
import com.peekaboo.design_system.Main2
import com.peekaboo.design_system.Main3
import com.peekaboo.design_system.Question
import com.peekaboo.design_system.R
import com.peekaboo.design_system.SkinCondition
import com.peekaboo.design_system.White2
import com.peekaboo.design_system.White3

@Composable
fun HomeScreen() {
    HomeContent()
}

@Composable
fun HomeContent() {
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

        HomeDiseaseBanner()

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
                .padding(top = 43.dp, start = 20.dp)
        )

        HomeDiagnosingBox()

        HomeDiagnosisHistory()

        HomeDiseaseTypeList()
    }
}

@Composable
fun HomeDiseaseBanner() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 3.dp, bottomEnd = 3.dp))
            .background(Main3)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 20.dp, end = 15.dp, top = 24.dp, bottom = 26.dp)
                .weight(1f)
        ) {
            Row {
                Text(
                    text = "콜레라",
                    color = White2,
                    style = BaeBaeTypo.Head1,
                )

                Text(
                    text = "앙골라",
                    color = Gray2,
                    style = BaeBaeTypo.Body1,
                    modifier = Modifier
                        .padding(top = 5.dp, start = 15.dp)
                )
            }

            Text(
                text = "25년 1월 초부터 3월 23일까지 앙골라에서 콜레라 누적 8,543명 발생, 329명 사망 보고",
                color = Gray2,
                style = BaeBaeTypo.Caption1,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
        }

        Box(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .size(80.dp)
                .clip(RoundedCornerShape(3.dp))
                .background(Gray2),
        )

        Spacer(modifier = Modifier.width(30.dp))
    }
}

@Composable
fun HomeDiagnosingBox() {
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

        Box(
            modifier = Modifier
                .padding(horizontal = 45.dp)
                .padding(top = 30.dp, bottom = 20.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .background(Main2)
        ) {
            Text(
                text = HomeDiagnosingBtn,
                color = White3,
                style = BaeBaeTypo.Caption1,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 16.dp)
            )
        }
    }
}

@Composable
fun HomeDiagnosisHistory() {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(White2)
            .border(1.dp, color = Gray1, RoundedCornerShape(6.dp))
            .fillMaxWidth(),
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
fun HomeDiseaseTypeList() {
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
                .padding(top = 22.dp)
                .padding(horizontal = 18.dp),
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
        items(5) {
            DiseaseTypeItem()
        }
    }
}

@Composable
fun DiseaseTypeItem() {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(width = 35.dp, height = 40.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(Gray2)
        )

        Text(
            text = "아토피 피부염",
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