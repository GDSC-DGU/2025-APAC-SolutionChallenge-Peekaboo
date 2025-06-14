package com.peekaboo.onboarding.personal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.BirthInputHint
import com.peekaboo.design_system.BirthInputSemiTitle
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.BloodTypeSemiTitle
import com.peekaboo.design_system.Next
import com.peekaboo.design_system.OnBoardingTitle
import com.peekaboo.design_system.PersonalInputTitle
import com.peekaboo.design_system.SexChoiceSemiTitle
import com.peekaboo.design_system.White3
import com.peekaboo.domain.entity.request.CreateUserModel
import com.peekaboo.onboarding.type.BloodType
import com.peekaboo.onboarding.type.GenderType
import com.peekaboo.ui.common.appbar.TopBar
import com.peekaboo.ui.common.button.BottomRectangleBtn
import com.peekaboo.ui.common.content.CourseNumber
import com.peekaboo.ui.common.item.SelectItem
import com.peekaboo.ui.common.item.TextFieldBox
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun PersonalInputScreen(
    userModel: SharedFlow<CreateUserModel>,
    goToSkinSelectPage: (CreateUserModel) -> Unit,
) {

    val viewModel: PersonalInputViewmodel = hiltViewModel()
    val uiState: PersonalInputPageState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(userModel) {
        userModel.collect {
            viewModel.setUserModel(it)
        }
    }

    PersonalInputContent(
        birthInput = uiState.birthInput,
        onBirthValueChange = { newValue ->
            viewModel.onBirthValueChange(newValue)
        },
        selectedSex = uiState.selectedSex,
        onSelectSex = { sex ->
            viewModel.setSelectedSex(sex)
        },
        selectedBloodType = uiState.bloodType,
        onSelectBloodType = { bloodType ->
            viewModel.setBloodType(bloodType)
        },
        isBirthValid = viewModel.isBirthValid(),
        onClickBtnAction = { goToSkinSelectPage(viewModel.updateUserModel()) }
    )
}

@Composable
fun PersonalInputContent(
    birthInput: String = "",
    onBirthValueChange: (String) -> Unit = {},
    selectedSex: String = "",
    onSelectSex: (String) -> Unit = {},
    selectedBloodType: String = "",
    onSelectBloodType: (String) -> Unit = {},
    isBirthValid: Boolean = false,
    onClickBtnAction: () -> Unit = {},
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
                onBirthValueChange = onBirthValueChange
            )

            SexChoiceBox(
                selectedSex = selectedSex,
                onSelectSex = onSelectSex
            )

            BloodTypeChoiceBox(
                selectedBloodType = selectedBloodType,
                onSelectBloodType = onSelectBloodType
            )
        }

        BottomRectangleBtn(
            horizontalPadding = 20,
            btnText = Next,
            isBtnValid = (selectedSex.isNotEmpty() && selectedBloodType.isNotEmpty() && isBirthValid),
            onClickAction = onClickBtnAction
        )

        Spacer(modifier = Modifier.height(36.dp))
    }
}

@Composable
fun BirthInputBox(
    birthInput: String,
    onBirthValueChange: (String) -> Unit,
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
        onValueChange = onBirthValueChange,
        horizontalPadding = 25
    )
}

@Composable
fun SexChoiceBox(
    selectedSex: String,
    onSelectSex: (String) -> Unit,
) {
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
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        GenderType.entries.forEach { sex ->
            SelectItem(
                itemText = sex.content,
                modifier = Modifier.weight(1f),
                onSelectItem = { onSelectSex(sex.api) },
                isItemSelected = (sex.api == selectedSex)
            )
        }
    }
}

@Composable
fun BloodTypeChoiceBox(
    selectedBloodType: String,
    onSelectBloodType: (String) -> Unit,
) {
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
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BloodType.entries.take(2).forEach { bloodType ->
            SelectItem(
                itemText = bloodType.content,
                modifier = Modifier.weight(1f),
                onSelectItem = { onSelectBloodType(bloodType.api) },
                isItemSelected = (bloodType.api == selectedBloodType)
            )
        }
    }

    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BloodType.entries.drop(2).forEach { bloodType ->
            SelectItem(
                itemText = bloodType.content,
                modifier = Modifier.weight(1f),
                onSelectItem = { onSelectBloodType(bloodType.api) },
                isItemSelected = (bloodType.api == selectedBloodType)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPersonalInput() {
    PersonalInputContent()
}