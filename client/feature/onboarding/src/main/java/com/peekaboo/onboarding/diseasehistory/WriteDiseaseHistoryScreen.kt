package com.peekaboo.onboarding.diseasehistory

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
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.CautionNotice
import com.peekaboo.design_system.DiseaseHistoryInputHint
import com.peekaboo.design_system.DiseaseHistoryInputSemiTitle
import com.peekaboo.design_system.DiseaseHistoryInputTitle
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
fun WriteDiseaseHistoryScreen(
    userModel: SharedFlow<CreateUserModel>,
    goToMainPage: (CreateUserModel) -> Unit
) {

    val viewModel: WriteDiseaseHistoryViewmodel = hiltViewModel()
    val uiState: WriteDiseaseHistoryPageState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(userModel) {
        userModel.collect {
            viewModel.setUserModel(it)
        }
    }

    WriteDiseaseHistoryContent(
        diseaseHistoryInputList = uiState.diseaseHistoryInputList,
        onDiseaseHistoryListChange = { index, content ->
            viewModel.onDiseaseHistoryValueChange(index, content)
        },
        onClickListAddBtn = { viewModel.addDiseaseHistoryList() },
        onClickBtnAction = { goToMainPage(viewModel.updateUserModel()) },
        onClickDiseaseDeleteBtn = { index -> viewModel.deleteDiseaseHistory(index) }
    )
}

@Composable
fun WriteDiseaseHistoryContent(
    diseaseHistoryInputList: List<InputDescriptionModel> = listOf(InputDescriptionModel()),
    onDiseaseHistoryListChange: (Int, String) -> Unit = { index: Int, content: String -> },
    onClickListAddBtn: () -> Unit = {},
    onClickBtnAction: () -> Unit = {},
    onClickDiseaseDeleteBtn: (Int) -> Unit = {},
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
                currentNumber = 5,
                totalNumber = 5,
                paddingTop = 35,
                paddingStart = 20
            )

            Text(
                text = DiseaseHistoryInputTitle,
                color = Black1,
                style = BaeBaeTypo.Body1,
                modifier = Modifier
                    .padding(top = 10.dp, start = 20.dp)
            )

            Text(
                text = DiseaseHistoryInputSemiTitle,
                color = Gray3,
                style = BaeBaeTypo.Body4,
                modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            WriteDiseaseHistoryInputList(
                modifier = Modifier
                    .align(Alignment.End),
                diseaseHistoryInput = diseaseHistoryInputList,
                onDiseaseHistoryChange = onDiseaseHistoryListChange,
                onClickAddBtn = onClickListAddBtn,
                onClickDiseaseDeleteBtn = onClickDiseaseDeleteBtn
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
            isBtnValid = diseaseHistoryInputList.all { it.description.isNotEmpty() },
            onClickAction = onClickBtnAction
        )

        Spacer(modifier = Modifier.height(36.dp))
    }
}

@Composable
fun WriteDiseaseHistoryInputList(
    modifier: Modifier,
    diseaseHistoryInput: List<InputDescriptionModel>,
    onDiseaseHistoryChange: (Int, String) -> Unit,
    onClickAddBtn: () -> Unit,
    onClickDiseaseDeleteBtn: (Int) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(
            diseaseHistoryInput,
            key = { index, item -> index }) { index, diseaseHistoryInput ->
            WriteDiseaseHistoryInputBox(
                diseaseInput = diseaseHistoryInput.description,
                onDiseaseHistoryListChange = { onDiseaseHistoryChange(index, it) },
                index = index,
                onClickDeleteBtn = { onClickDiseaseDeleteBtn(index) }
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
fun WriteDiseaseHistoryInputBox(
    diseaseInput: String,
    onDiseaseHistoryListChange: (String) -> Unit,
    index: Int,
    onClickDeleteBtn: () -> Unit,
) {
    TextFieldBox(
        textInput = diseaseInput,
        onValueChange = onDiseaseHistoryListChange,
        horizontalPadding = 25,
        hintText = DiseaseHistoryInputHint,
        isDeleteBtnValid = (index > 0),
        onClickDeleteBtn = onClickDeleteBtn
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewWriteDiseaseHistory() {
    WriteDiseaseHistoryContent()
}