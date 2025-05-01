package com.peekaboo.onboarding.diseasehistory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.peekaboo.design_system.Add
import com.peekaboo.design_system.AllergyCautionNotice
import com.peekaboo.design_system.AllergyInputHint
import com.peekaboo.design_system.AllergyInputSemiTitle
import com.peekaboo.design_system.AllergyInputTitle
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.Gray3
import com.peekaboo.design_system.Next
import com.peekaboo.design_system.OnBoardingTitle
import com.peekaboo.design_system.White3
import com.peekaboo.ui.common.appbar.TopBar
import com.peekaboo.ui.common.button.BottomRectangleBtn
import com.peekaboo.ui.common.content.CautionNoticeBox
import com.peekaboo.ui.common.content.CourseNumber
import com.peekaboo.ui.common.item.ChipItem
import com.peekaboo.ui.common.item.TextFieldBox

@Composable
fun DiseaseHistoryScreen() {
    DiseaseHistoryContent()
}

@Composable
fun DiseaseHistoryContent(
    diseaseHistoryInput: String = "",
    onDiseaseHistoryChange: (String) -> Unit = {},
) {
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
                currentNumber = 4,
                totalNumber = 5,
                paddingTop = 35,
                paddingStart = 20
            )

            Text(
                text = AllergyInputTitle,
                color = Black1,
                style = BaeBaeTypo.Body1,
                modifier = Modifier
                    .padding(top = 10.dp, start = 20.dp)
            )

            Text(
                text = AllergyInputSemiTitle,
                color = Gray3,
                style = BaeBaeTypo.Body4,
                modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            DiseaseHistoryInputBox(
                diseaseHistoryInput = diseaseHistoryInput,
                onDiseaseHistoryChange = onDiseaseHistoryChange,
                modifier = Modifier
                    .align(Alignment.End)
            )
        }

        CautionNoticeBox(
            horizontalPadding = 20,
            cautionText = AllergyCautionNotice
        )

        Spacer(modifier = Modifier.height(30.dp))

        BottomRectangleBtn(
            horizontalPadding = 20,
            btnText = Next
        )
    }
}

@Composable
fun DiseaseHistoryInputBox(
    modifier: Modifier,
    diseaseHistoryInput: String,
    onDiseaseHistoryChange: (String) -> Unit,
) {
    TextFieldBox(
        textInput = diseaseHistoryInput,
        onValueChange = onDiseaseHistoryChange,
        horizontalPadding = 25,
        hintText = AllergyInputHint
    )

    Row(
        modifier = modifier
            .padding(top = 10.dp, end = 24.dp)
    ) {
        ChipItem(chipText = Add)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDiseaseHistory() {
    DiseaseHistoryContent()
}