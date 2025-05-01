package com.peekaboo.onboarding.personal

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
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.BirthInputHint
import com.peekaboo.design_system.BirthInputSemiTitle
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.BloodTypeA
import com.peekaboo.design_system.BloodTypeAB
import com.peekaboo.design_system.BloodTypeB
import com.peekaboo.design_system.BloodTypeO
import com.peekaboo.design_system.BloodTypeSemiTitle
import com.peekaboo.design_system.FeMale
import com.peekaboo.design_system.Male
import com.peekaboo.design_system.Next
import com.peekaboo.design_system.OnBoardingTitle
import com.peekaboo.design_system.PersonalInputTitle
import com.peekaboo.design_system.SexChoiceSemiTitle
import com.peekaboo.design_system.White3
import com.peekaboo.ui.common.appbar.TopBar
import com.peekaboo.ui.common.button.BottomRectangleBtn
import com.peekaboo.ui.common.content.CourseNumber
import com.peekaboo.ui.common.item.SelectItem
import com.peekaboo.ui.common.item.TextFieldBox

@Composable
fun PersonalInputScreen() {
    PersonalInputContent()
}

@Composable
fun PersonalInputContent(
    birthInput: String = "",
    onBirthInput: (String) -> Unit = {},
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
                currentNumber = 2,
                totalNumber = 5,
                paddingTop = 35,
                paddingStart = 20
            )

            Text(
                text = PersonalInputTitle,
                color = Black1,
                style = BaeBaeTypo.Body1,
                modifier = Modifier
                    .padding(top = 10.dp, start = 20.dp)
            )

            BirthInputBox(
                birthInput = birthInput,
                onBirthInput = onBirthInput
            )

            SexChoiceBox()

            BloodTypeChoiceBox()
        }

        BottomRectangleBtn(
            horizontalPadding = 20,
            btnText = Next
        )
    }
}

@Composable
fun BirthInputBox(
    birthInput: String,
    onBirthInput: (String) -> Unit,
) {
    Text(
        text = BirthInputSemiTitle,
        color = Black1,
        style = BaeBaeTypo.Body3,
        modifier = Modifier
            .padding(top = 41.dp, start = 20.dp, bottom = 17.dp)
    )

    TextFieldBox(
        textInput = birthInput,
        hintText = BirthInputHint,
        onValueChange = onBirthInput,
        horizontalPadding = 25
    )
}

@Composable
fun SexChoiceBox() {
    Text(
        text = SexChoiceSemiTitle,
        color = Black1,
        style = BaeBaeTypo.Body3,
        modifier = Modifier
            .padding(top = 25.dp, start = 20.dp)
    )

    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 17.dp)
            .fillMaxWidth()
    ) {
        SelectItem(
            itemText = Male,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(16.dp))

        SelectItem(
            itemText = FeMale,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun BloodTypeChoiceBox() {
    Text(
        text = BloodTypeSemiTitle,
        color = Black1,
        style = BaeBaeTypo.Body3,
        modifier = Modifier
            .padding(top = 25.dp, start = 20.dp)
    )

    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 17.dp, bottom = 11.dp)
            .fillMaxWidth()
    ) {
        SelectItem(
            itemText = BloodTypeA,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(16.dp))

        SelectItem(
            itemText = BloodTypeB,
            modifier = Modifier.weight(1f)
        )
    }

    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
    ) {
        SelectItem(
            itemText = BloodTypeAB,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(16.dp))

        SelectItem(
            itemText = BloodTypeO,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPersonalInput() {
    PersonalInputContent()
}