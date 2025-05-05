package com.peekaboo.diagnosisquick.detail

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.peekaboo.design_system.BackToMain
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Gray2
import com.peekaboo.design_system.Gray3
import com.peekaboo.design_system.Main3
import com.peekaboo.design_system.QuickDiagnosisTitle
import com.peekaboo.design_system.QuickDiseaseAdditionalBtn
import com.peekaboo.design_system.White2
import com.peekaboo.design_system.White3
import com.peekaboo.ui.common.appbar.TopBar
import com.peekaboo.ui.common.button.BottomRectangleBtn
import com.peekaboo.ui.common.content.DiseaseDetail

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
                DiseaseDetail()
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDetailQuick() {
    DetailQuickContent()
}