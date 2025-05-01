package com.peekaboo.onboarding.language

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.peekaboo.design_system.America
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.English
import com.peekaboo.design_system.Korea
import com.peekaboo.design_system.Korean
import com.peekaboo.design_system.LanguageChoiceSemiTitle
import com.peekaboo.design_system.LanguageChoiceTitle
import com.peekaboo.design_system.LocationChoiceSemiTitle
import com.peekaboo.design_system.Next
import com.peekaboo.design_system.OnBoardingTitle
import com.peekaboo.design_system.White3
import com.peekaboo.ui.common.appbar.TopBar
import com.peekaboo.ui.common.button.BottomRectangleBtn
import com.peekaboo.ui.common.content.CourseNumber
import com.peekaboo.ui.common.item.SelectItem

@Composable
fun LanguageChoiceScreen() {

    LanguageChoiceContent()
}

@Composable
fun LanguageChoiceContent() {
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
                titleText = OnBoardingTitle
            )

            CourseNumber(
                currentNumber = 1,
                totalNumber = 5,
                paddingTop = 35,
                paddingStart = 20
            )

            Text(
                text = LanguageChoiceTitle,
                color = Black1,
                style = BaeBaeTypo.Body1,
                modifier = Modifier
                    .padding(top = 10.dp, start = 20.dp)
            )

            LocationChoiceBox()

            LanguageChoiceBox()
        }

        BottomRectangleBtn(
            horizontalPadding = 20,
            btnText = Next
        )
    }
}

@Composable
fun LocationChoiceBox() {
    Text(
        text = LocationChoiceSemiTitle,
        color = Black1,
        style = BaeBaeTypo.Body3,
        modifier = Modifier
            .padding(top = 60.dp, start = 20.dp)
    )

    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 17.dp)
            .fillMaxWidth()
    ) {
        SelectItem(
            itemText = Korea,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(16.dp))

        SelectItem(
            itemText = America,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun LanguageChoiceBox() {
    Text(
        text = LanguageChoiceSemiTitle,
        color = Black1,
        style = BaeBaeTypo.Body3,
        modifier = Modifier
            .padding(top = 18.dp, start = 20.dp)
    )

    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 17.dp)
            .fillMaxWidth()
    ) {
        SelectItem(
            itemText = Korean,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(16.dp))

        SelectItem(
            itemText = English,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLanguageChoice() {
    LanguageChoiceContent()
}