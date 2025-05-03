package com.peekaboo.onboarding.allergy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.peekaboo.design_system.Add
import com.peekaboo.design_system.AllergyInputHint
import com.peekaboo.design_system.AllergyInputSemiTitle
import com.peekaboo.design_system.AllergyInputTitle
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.CautionNotice
import com.peekaboo.design_system.Gray3
import com.peekaboo.design_system.Next
import com.peekaboo.design_system.OnBoardingTitle
import com.peekaboo.design_system.White3
import com.peekaboo.domain.entity.request.CreateUserModel
import com.peekaboo.domain.entity.request.InputDescriptionModel
import com.peekaboo.ui.common.appbar.TopBar
import com.peekaboo.ui.common.button.BottomRectangleBtn
import com.peekaboo.ui.common.content.CautionNoticeBox
import com.peekaboo.ui.common.content.CourseNumber
import com.peekaboo.ui.common.item.ChipItem
import com.peekaboo.ui.common.item.TextFieldBox
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun AllergyExistScreen(
    userModel: SharedFlow<CreateUserModel>,
    goToDiseaseHistoryPage: (CreateUserModel) -> Unit = {},
) {

    val viewModel: AllergyExistViewModel = hiltViewModel()
    val uiState: AllergyExistPageState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(userModel) {
        userModel.collect {
            viewModel.setUserModel(it)
        }
    }

    AllergyExistContent(
        allergyInputList = uiState.allergyExistInputList,
        onAllergyListChange = { index, content ->
            viewModel.onAllergyExistValueChange(
                index,
                content
            )
        },
        onClickListAddBtn = { viewModel.addAllergyExistList() },
        onClickBtnAction = { goToDiseaseHistoryPage(viewModel.updateUserModel()) },
        onClickAllergyDeleteBtn = { index -> viewModel.deleteAllergyExist(index) }
    )
}

@Composable
fun AllergyExistContent(
    allergyInputList: List<InputDescriptionModel> = listOf(InputDescriptionModel()),
    onAllergyListChange: (Int, String) -> Unit = { index: Int, content: String -> },
    onClickListAddBtn: () -> Unit = {},
    onClickBtnAction: () -> Unit = {},
    onClickAllergyDeleteBtn: (Int) -> Unit = {}
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

            AllergyExistInputList(
                modifier = Modifier
                    .align(Alignment.End),
                allergyInputList = allergyInputList,
                onAllergyListChange = onAllergyListChange,
                onClickAddBtn = onClickListAddBtn,
                onClickAllergyDeleteBtn = onClickAllergyDeleteBtn
            )
        }

        CautionNoticeBox(
            horizontalPadding = 20,
            cautionText = CautionNotice
        )

        Spacer(modifier = Modifier.height(30.dp))

        BottomRectangleBtn(
            horizontalPadding = 20,
            btnText = Next,
            isBtnValid = allergyInputList.all { it.description.isNotEmpty() },
            onClickAction = onClickBtnAction
        )

        Spacer(modifier = Modifier.height(36.dp))
    }
}

@Composable
fun AllergyExistInputList(
    modifier: Modifier,
    allergyInputList: List<InputDescriptionModel>,
    onAllergyListChange: (Int, String) -> Unit,
    onClickAddBtn: () -> Unit,
    onClickAllergyDeleteBtn: (Int) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(allergyInputList, key = { index, item -> index }) { index, allergyInput ->
            AllergyExistInputBox(
                allergyInput = allergyInput.description,
                onAllergyListChange = { onAllergyListChange(index, it) },
                index = index,
                onClickDeleteBtn = { onClickAllergyDeleteBtn(index) }
            )
        }
    }

    Row(
        modifier = modifier
            .padding(top = 10.dp, end = 24.dp)
    ) {
        ChipItem(
            chipText = Add,
            onClickAction = onClickAddBtn
        )
    }
}

@Composable
fun AllergyExistInputBox(
    allergyInput: String,
    onAllergyListChange: (String) -> Unit,
    index: Int,
    onClickDeleteBtn: () -> Unit
) {
    TextFieldBox(
        textInput = allergyInput,
        onValueChange = onAllergyListChange,
        horizontalPadding = 25,
        hintText = AllergyInputHint,
        isDeleteBtnValid = (index > 0),
        onClickDeleteBtn = onClickDeleteBtn
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAllergyExist() {
    AllergyExistContent()
}