package com.peekaboo.diagnosisquick.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.peekaboo.design_system.BackToMain
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.Gray1
import com.peekaboo.design_system.Gray2
import com.peekaboo.design_system.Gray3
import com.peekaboo.design_system.Main2
import com.peekaboo.design_system.Main3
import com.peekaboo.design_system.QuickDiagnosisTitle
import com.peekaboo.design_system.QuickDiseaseAdditionalBtn
import com.peekaboo.design_system.QuickDiseaseFirstAid
import com.peekaboo.design_system.QuickDiseaseFirstAidMild
import com.peekaboo.design_system.QuickDiseaseFirstAidSevere
import com.peekaboo.design_system.QuickDiseaseMedicine
import com.peekaboo.design_system.QuickDiseasePrecautions
import com.peekaboo.design_system.QuickDiseasePreventiveMeasure
import com.peekaboo.design_system.QuickDiseaseSymptomsCategory
import com.peekaboo.design_system.QuickDiseaseSymptomsRating
import com.peekaboo.design_system.QuickDiseaseSymptomsReason
import com.peekaboo.design_system.QuickDiseaseSymptomsSite
import com.peekaboo.design_system.QuickDiseaseSymptomsTitle
import com.peekaboo.design_system.QuickDiseaseSymptomsType
import com.peekaboo.design_system.White2
import com.peekaboo.design_system.White3
import com.peekaboo.ui.common.appbar.TopBar
import com.peekaboo.ui.common.button.BottomRectangleBtn

@Composable
fun DetailQuickScreen() {
    DetailQuickContent()
}

@Composable
fun DetailQuickContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White3)
    ) {
        TopBar(
            titleText = QuickDiagnosisTitle,
            isIconValid = true
        )

        DetailDescriptionBanner()

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) {
            item {
                DetailDescriptionContent()
            }
        }

        BottomRectangleBtn(
            horizontalPadding = 20,
            btnText = QuickDiseaseAdditionalBtn,
            isBtnValid = true
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
        )
    }
}

@Composable
fun DetailDescriptionBanner() {
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
                text = "아토피 피부염",
                color = White2,
                style = BaeBaeTypo.Head1,
            )

            Text(
                text = "만성적인 염증성 피부질환, 주로 영유아기에 시작되어 피부의 건조, 가려움, 반복적인 염증이 특징",
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

@Composable
fun DetailDescriptionContent() {
    DiseaseRating()
    DiseaseSymptoms()
    DiseaseType()
    DiseaseDrugs()
    DiseaseFirstAid()
    DiseasePreventive()
    DiseaseCaution()
}

@Composable
fun DiseaseRating(
    rating: Int = 4,
) {
    Spacer(modifier = Modifier.height(30.dp))

    DescriptionTitle(title = QuickDiseaseSymptomsRating)

    BoxWithConstraints(
        modifier = Modifier
            .padding(top = 13.dp, start = 23.dp, end = 23.dp)
    ) {
        Box(
            modifier = Modifier
                .width(maxWidth)
                .height(5.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(Gray1)
        )

        Box(
            modifier = Modifier
                .width(maxWidth * 0.25f)
                .height(5.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(Main2)
        )

        LazyRow(
            modifier = Modifier
                .padding(top = 6.dp)
                .fillMaxWidth(),
        ) {
            items(4) { index ->
                Text(
                    text = "4급",
                    color = if (index + rating == 4) Main2 else Gray3,
                    style = BaeBaeTypo.Caption2,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .width(maxWidth * 0.25f)
                )
            }
        }

        LazyRow(
            modifier = Modifier
                .padding(top = 18.dp)
                .fillMaxWidth(),
        ) {
            items(4) { index ->
                Text(
                    text = "감시.예방 중심",
                    color = if (index + rating == 4) Main2 else Color.Transparent,
                    style = BaeBaeTypo.Caption2,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .width(maxWidth * 0.25f)
                )
            }
        }
    }

    DescriptionDivider()
}

@Composable
fun DiseaseSymptoms() {
    DescriptionTitle(title = QuickDiseaseSymptomsTitle)

    LazyRow(
        modifier = Modifier
            .padding(top = 10.dp),
        contentPadding = PaddingValues(horizontal = 23.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(2) {
            DiseaseSymptomItem()
        }
    }

    DescriptionDivider()
}

@Composable
fun DiseaseSymptomItem() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(Gray1)
    ) {
        Text(
            text = "피부 붉은 발진",
            color = Gray3,
            style = BaeBaeTypo.Caption4,
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 12.dp)
        )
    }
}

@Composable
fun DiseaseType() {
    DescriptionTitle(title = QuickDiseaseSymptomsCategory)

    Spacer(modifier = Modifier.height(15.dp))

    DescriptionSemiContent(
        title = QuickDiseaseSymptomsType,
        content = "급성과 만성으로 구분되며, 급성은 붉은 발진과 진물 등이 나타나는 단계이며, 만성은 피부가 두꺼워지고 지속적인 가려움이 나타남",
    )

    Spacer(modifier = Modifier.height(15.dp))

    DescriptionSemiContent(
        title = QuickDiseaseSymptomsSite,
        content = "급성과 만성으로 구분되며, 급성은 붉은 발진과 진물 등이 나타나는 단계이며, 만성은 피부가 두꺼워지고 지속적인 가려움이 나타남",
    )

    Spacer(modifier = Modifier.height(15.dp))

    DescriptionSemiContent(
        title = QuickDiseaseSymptomsReason,
        content = "급성과 만성으로 구분되며, 급성은 붉은 발진과 진물 등이 나타나는 단계이며, 만성은 피부가 두꺼워지고 지속적인 가려움이 나타남",
    )

    DescriptionDivider()
}

@Composable
fun DiseaseDrugs() {
    DescriptionTitle(title = QuickDiseaseMedicine)

    LazyRow(
        modifier = Modifier
            .padding(top = 10.dp),
        contentPadding = PaddingValues(horizontal = 23.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(2) {
            DiseaseDrugsItem()
        }
    }

    DescriptionDivider()
}

@Composable
fun DiseaseDrugsItem() {
    Row {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 100.dp, bottomStart = 100.dp))
                .background(Main2)
        ) {
            Text(
                text = "국소 스테로이드 크림",
                color = White3,
                style = BaeBaeTypo.Caption4,
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 12.dp)
            )
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topEnd = 100.dp, bottomEnd = 100.dp))
                .background(Gray1)
        ) {
            Text(
                text = "염증 완화",
                color = Black1,
                style = BaeBaeTypo.Caption4,
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 12.dp)
            )
        }
    }
}

@Composable
fun DiseaseFirstAid() {
    DescriptionTitle(title = QuickDiseaseFirstAid)

    Spacer(modifier = Modifier.height(15.dp))

    DescriptionSemiContent(
        title = QuickDiseaseFirstAidMild,
        content = "급성과 만성으로 구분되며, 급성은 붉은 발진과 진물 등이 나타나는 단계이며, 만성은 피부가 두꺼워지고 지속적인 가려움이 나타남",
    )

    Spacer(modifier = Modifier.height(15.dp))

    DescriptionSemiContent(
        title = QuickDiseaseFirstAidSevere,
        content = "급성과 만성으로 구분되며, 급성은 붉은 발진과 진물 등이 나타나는 단계이며, 만성은 피부가 두꺼워지고 지속적인 가려움이 나타남",
    )

    DescriptionDivider()
}

@Composable
fun DiseasePreventive() {
    DescriptionTitle(title = QuickDiseasePreventiveMeasure)

    DiseaseSemiContentList()

    DescriptionDivider()
}

@Composable
fun DiseaseCaution() {
    DescriptionTitle(title = QuickDiseasePrecautions)

    DiseaseSemiContentList()

    Spacer(modifier = Modifier.height(50.dp))
}

@Composable
fun DescriptionTitle(
    title: String,
) {
    Text(
        text = title,
        color = Black1,
        style = BaeBaeTypo.Body1,
        modifier = Modifier
            .padding(start = 20.dp)
    )
}

@Composable
fun DescriptionSemiContent(
    title: String,
    content: String,
) {
    Box(
        modifier = Modifier
            .padding(start = 23.dp, end = 20.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            color = Black1,
            style = BaeBaeTypo.Body3,
            modifier = Modifier
        )

        Text(
            text = content,
            color = Gray3,
            style = BaeBaeTypo.Body3,
            modifier = Modifier
                .padding(start = 60.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun DiseaseSemiContentList() {
    Column(
        modifier = Modifier
            .padding(top = 15.dp, start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        repeat(3) {
            DiseaseSemiContentListItem()
        }
    }
}

@Composable
fun DiseaseSemiContentListItem() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(Main2, shape = CircleShape)
        )

        Text(
            text = "피부 보습 생활화하기",
            color = Gray3,
            style = BaeBaeTypo.Body3,
            modifier = Modifier
                .padding(start = 15.dp)
        )
    }
}

@Composable
fun DescriptionDivider() {
    Divider(
        modifier = Modifier
            .padding(20.dp)
            .border(2.dp, color = Gray1)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDetailQuick() {
    DetailQuickContent()
}