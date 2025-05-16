package com.peekaboo.onboarding.skin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.Gray3
import com.peekaboo.design_system.Main2
import com.peekaboo.design_system.Next
import com.peekaboo.design_system.OnBoardingTitle
import com.peekaboo.design_system.SkinColorSelectSemiTitle
import com.peekaboo.design_system.SkinColorSelectTitle
import com.peekaboo.design_system.White3
import com.peekaboo.domain.entity.request.CreateUserModel
import com.peekaboo.onboarding.type.SkinColorType
import com.peekaboo.ui.common.appbar.TopBar
import com.peekaboo.ui.common.button.BottomRectangleBtn
import com.peekaboo.ui.common.content.CourseNumber
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun SkinColorSelectScreen(
    userModel: SharedFlow<CreateUserModel>,
    goToAllergyPage: (CreateUserModel) -> Unit,
) {

    val viewModel: SkinColorViewModel = hiltViewModel()
    val uiState: SkinColorPageState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(userModel) {
        userModel.collect {
            viewModel.setUserModel(it)
        }
    }

    SkinColorSelectContent(
        selectedColor = uiState.selectedColor,
        onSelectColor = { color ->
            viewModel.setColor(color)
        },
        onClickBtnAction = { goToAllergyPage(viewModel.updateUserModel()) }
    )
}

@Composable
fun SkinColorSelectContent(
    selectedColor: String = "",
    onSelectColor: (String) -> Unit = {},
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
                currentNumber = 3,
                totalNumber = 5,
                paddingTop = 35,
                paddingStart = 20
            )

            Text(
                text = SkinColorSelectTitle,
                color = Black1,
                style = BaeBaeTypo.Body1,
                modifier = Modifier
                    .padding(top = 10.dp, start = 20.dp)
            )

            Text(
                text = SkinColorSelectSemiTitle,
                color = Gray3,
                style = BaeBaeTypo.Body4,
                modifier = Modifier
                    .padding(top = 15.dp, start = 20.dp)
            )

            SkinColorList(
                selectedColor = selectedColor,
                onSelectColor = onSelectColor
            )
        }

        BottomRectangleBtn(
            horizontalPadding = 20,
            btnText = Next,
            isBtnValid = selectedColor.isNotEmpty(),
            onClickAction = onClickBtnAction
        )

        Spacer(modifier = Modifier.height(36.dp))
    }
}

@Composable
fun SkinColorList(
    selectedColor: String,
    onSelectColor: (String) -> Unit,
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 81.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items(SkinColorType.entries) { color ->
            SkinColorItem(
                skinColor = color.color,
                onSelect = { onSelectColor(color.apiType) },
                isSelected = (color.apiType == selectedColor)
            )
        }
    }
}

@Composable
fun SkinColorItem(
    skinColor: Color,
    isSelected: Boolean,
    onSelect: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(skinColor)
            .border(
                3.dp,
                color = if (isSelected) Main2 else Color.Transparent,
                RoundedCornerShape(5.dp)
            )
            .clickable(
                onClick = onSelect
            )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSkinColorSelect() {
    SkinColorSelectContent()
}