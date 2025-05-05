package com.peekaboo.diagnosis.selectarea

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.DiagnosisSelectArea
import com.peekaboo.design_system.DiagnosisSelectAreaBack
import com.peekaboo.design_system.DiagnosisSelectAreaFront
import com.peekaboo.design_system.DiagnosisSelectAreaSide
import com.peekaboo.design_system.DiagnosisTitle
import com.peekaboo.design_system.Gray1
import com.peekaboo.design_system.Gray3
import com.peekaboo.design_system.Next
import com.peekaboo.design_system.White2
import com.peekaboo.design_system.White3
import com.peekaboo.domain.entity.request.DiagnosisModel
import com.peekaboo.ui.common.appbar.TopBar
import com.peekaboo.ui.common.button.BottomRectangleBtn
import com.peekaboo.ui.common.content.CourseNumber
import com.peekaboo.ui.common.item.ChipItem

@Composable
fun SelectAreaScreen(
    goToPicturePage: (DiagnosisModel) -> Unit,
) {

    val viewModel: SelectAreaViewModel = hiltViewModel()
    val uiState: SelectAreaPageState by viewModel.uiState.collectAsStateWithLifecycle()

    val interactionSource = remember { MutableInteractionSource() }

    SelectAreaContent(
        interactionSource = interactionSource,
        onClickNextBtn = { goToPicturePage(viewModel.updateDiagnosisContent()) },
        onSelectShapeChip = { viewModel.setSelectedShape(it) },
        selectedShape = uiState.selectedShape,
        selectedShapeImg = uiState.selectedShapeImg,
        onClickShapePosition = { viewModel.setSelectedArea(it) },
        selectedArea = uiState.selectedArea
    )
}

@Composable
fun SelectAreaContent(
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    onClickNextBtn: () -> Unit = {},
    onSelectShapeChip: (String) -> Unit = {},
    selectedShape: String = "",
    selectedShapeImg: Int = 0,
    onClickShapePosition: (String) -> Unit = {},
    selectedArea: String = "",
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
                titleText = DiagnosisTitle,
                isIconValid = true
            )

            CourseNumber(
                currentNumber = 1,
                totalNumber = 3,
                paddingTop = 35,
                paddingStart = 20
            )

            Text(
                text = DiagnosisSelectArea,
                color = Black1,
                style = BaeBaeTypo.Body1,
                modifier = Modifier
                    .padding(top = 10.dp, start = 20.dp)
            )

            SelectAreaPictureShapeChip(
                onSelectShapeChip = onSelectShapeChip,
                selectedShape = selectedShape
            )

            SelectAreaPictureBox(
                selectedShapeImg = selectedShapeImg,
                onClickShapePosition = onClickShapePosition,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                interactionSource = interactionSource
            )
        }

        Box(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            if (selectedArea.isNotEmpty()) {
                ChipItem(
                    chipText = selectedArea
                )
            }
        }

        BottomRectangleBtn(
            horizontalPadding = 20,
            btnText = Next,
            isBtnValid = true,
            onClickAction = onClickNextBtn
        )

        Spacer(modifier = Modifier.height(36.dp))
    }
}

@Composable
fun SelectAreaPictureShapeChip(
    onSelectShapeChip: (String) -> Unit,
    selectedShape: String = DiagnosisSelectAreaFront,
) {
    Row(
        modifier = Modifier
            .padding(top = 35.dp, start = 23.dp, end = 23.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Gray1)
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 4.dp)
                .weight(1f)
                .clip(RoundedCornerShape(5.dp))
                .background(if (selectedShape == DiagnosisSelectAreaFront) White2 else Gray1)
                .clickable(
                    onClick = { onSelectShapeChip(DiagnosisSelectAreaFront) }
                )
        ) {
            Text(
                text = DiagnosisSelectAreaFront,
                color = if (selectedShape == DiagnosisSelectAreaFront) Black1 else Gray3,
                style = BaeBaeTypo.Body3,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 10.dp)
            )
        }

        Box(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .clip(RoundedCornerShape(5.dp))
                .weight(1f)
                .background(if (selectedShape == DiagnosisSelectAreaSide) White2 else Gray1)
                .clickable(
                    onClick = { onSelectShapeChip(DiagnosisSelectAreaSide) }
                )
        ) {
            Text(
                text = DiagnosisSelectAreaSide,
                color = if (selectedShape == DiagnosisSelectAreaSide) Black1 else Gray3,
                style = BaeBaeTypo.Body3,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 10.dp)
            )
        }

        Box(
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 4.dp)
                .weight(1f)
                .clip(RoundedCornerShape(5.dp))
                .background(if (selectedShape == DiagnosisSelectAreaBack) White2 else Gray1)
                .clickable(
                    onClick = { onSelectShapeChip(DiagnosisSelectAreaBack) }
                )
        ) {
            Text(
                text = DiagnosisSelectAreaBack,
                color = if (selectedShape == DiagnosisSelectAreaBack) Black1 else Gray3,
                style = BaeBaeTypo.Body3,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 10.dp)
            )
        }
    }
}

@Composable
fun SelectAreaPictureBox(
    selectedShapeImg: Int,
    onClickShapePosition: (String) -> Unit,
    modifier: Modifier,
    interactionSource: MutableInteractionSource,
) {

    Box(
        modifier = modifier
            .padding(top = 34.dp)
    ) {
        Image(
            painter = painterResource(id = selectedShapeImg),
            contentDescription = "body",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(width = 234.dp, height = 351.dp)
                .clip(RoundedCornerShape(5.dp))
        )

        // 머리
        Box(
            modifier = Modifier
                .size(width = 133.dp, height = 53.dp)
                .background(Color.Transparent)
                .align(Alignment.TopCenter)
                .clickable(
                    onClick = { onClickShapePosition("head") },
                    interactionSource = interactionSource,
                    indication = null
                )
        )
        //얼굴
        Box(
            modifier = Modifier
                .padding(top = 59.dp)
                .size(width = 71.dp, height = 50.dp)
                .background(Color.Transparent)
                .align(Alignment.TopCenter)
                .clickable(
                    onClick = { onClickShapePosition("face") },
                    interactionSource = interactionSource,
                    indication = null
                )
        )
        // 배
        Box(
            modifier = Modifier
                .padding(top = 154.dp)
                .size(width = 71.dp, height = 50.dp)
                .background(Color.Transparent)
                .align(Alignment.TopCenter)
                .clickable(
                    onClick = { onClickShapePosition("stomach") },
                    interactionSource = interactionSource,
                    indication = null
                )
        )
        // 엉덩이
        Box(
            modifier = Modifier
                .padding(top = 210.dp)
                .size(width = 71.dp, height = 39.dp)
                .background(Color.Transparent)
                .align(Alignment.TopCenter)
                .clickable(
                    onClick = { onClickShapePosition("hip") },
                    interactionSource = interactionSource,
                    indication = null
                )
        )
        // 다리
        Box(
            modifier = Modifier
                .padding(bottom = 7.dp)
                .size(width = 122.dp, height = 89.dp)
                .background(Color.Transparent)
                .align(Alignment.BottomCenter)
                .clickable(
                    onClick = { onClickShapePosition("leg") },
                    interactionSource = interactionSource,
                    indication = null
                )
        )
        // 팔
        Box(
            modifier = Modifier
                .padding(top = 117.dp, start = 8.dp)
                .size(width = 70.dp, height = 132.dp)
                .background(Color.Transparent)
                .align(Alignment.TopStart)
                .clickable(
                    onClick = { onClickShapePosition("arm") },
                    interactionSource = interactionSource,
                    indication = null
                )
        )
        Box(
            modifier = Modifier
                .padding(top = 117.dp, end = 8.dp)
                .size(width = 70.dp, height = 132.dp)
                .background(Color.Transparent)
                .align(Alignment.TopEnd)
                .clickable(
                    onClick = { onClickShapePosition("arm") },
                    interactionSource = interactionSource,
                    indication = null
                )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSelectArea() {
    SelectAreaContent()
}