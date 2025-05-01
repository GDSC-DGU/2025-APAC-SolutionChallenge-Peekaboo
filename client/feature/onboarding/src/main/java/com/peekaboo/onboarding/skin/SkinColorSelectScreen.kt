package com.peekaboo.onboarding.skin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.peekaboo.design_system.Next
import com.peekaboo.design_system.OnBoardingTitle
import com.peekaboo.design_system.SkinColorSelectSemiTitle
import com.peekaboo.design_system.SkinColorSelectTitle
import com.peekaboo.design_system.White3
import com.peekaboo.ui.common.appbar.TopBar
import com.peekaboo.ui.common.button.BottomRectangleBtn
import com.peekaboo.ui.common.content.CourseNumber

@Composable
fun SkinColorSelectScreen() {

    val viewModel: SkinColorViewModel = hiltViewModel()
    val uiState: SkinColorPageState by viewModel.uiState.collectAsStateWithLifecycle()

    SkinColorSelectContent(
        skinColors = uiState.skinColorList
    )
}

@Composable
fun SkinColorSelectContent(
    skinColors: List<Color> = emptyList(),
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
                skinColors = skinColors
            )
        }

        BottomRectangleBtn(
            horizontalPadding = 20,
            btnText = Next
        )
    }
}

@Composable
fun SkinColorList(
    skinColors: List<Color>,
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 81.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items(skinColors) { item ->
            SkinColorItem(
                skinColor = item
            )
        }
    }
}

@Composable
fun SkinColorItem(
    skinColor: Color,
) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(skinColor)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSkinColorSelect() {
    SkinColorSelectContent()
}