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
import androidx.compose.ui.graphics.ColorFilter
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
import com.peekaboo.design_system.DiagnosisTitle
import com.peekaboo.design_system.Gray1
import com.peekaboo.design_system.Gray3
import com.peekaboo.design_system.Main2
import com.peekaboo.design_system.Next
import com.peekaboo.design_system.R
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
                interactionSource = interactionSource,
                selectedBodyPosition = selectedArea
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
    selectedBodyPosition: String
) {
    when (selectedShapeImg == R.drawable.ic_body_front) {
        true -> {
            SelectAreaPictureBoxFront(
                selectedShapeImg = selectedShapeImg,
                onClickShapePosition = onClickShapePosition,
                modifier = modifier,
                interactionSource = interactionSource,
                selectedBodyPosition = selectedBodyPosition
            )
        }
        false -> {
            SelectAreaPictureBoxBack(
                selectedShapeImg = selectedShapeImg,
                onClickShapePosition = onClickShapePosition,
                modifier = modifier,
                interactionSource = interactionSource,
                selectedBodyPosition = selectedBodyPosition
            )
        }
    }
}

@Composable
fun SelectAreaPictureBoxFront(
    selectedShapeImg: Int,
    onClickShapePosition: (String) -> Unit,
    modifier: Modifier,
    interactionSource: MutableInteractionSource,
    selectedBodyPosition: String
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
        Image(
            painter = painterResource(id = R.drawable.ic_body_head),
            contentDescription = "body head",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == "head") Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 13.dp)
                .align(Alignment.TopCenter)
                .clickable(
                    onClick = { onClickShapePosition("head") },
                    interactionSource = interactionSource,
                    indication = null
                )
        )

        //얼굴
        Image(
            painter = painterResource(id = R.drawable.ic_body_face),
            contentDescription = "body face",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == "face") Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 61.dp)
                .align(Alignment.TopCenter)
                .clickable(
                    onClick = { onClickShapePosition("face") },
                    interactionSource = interactionSource,
                    indication = null
                )
        )

        // 배
        Image(
            painter = painterResource(id = R.drawable.ic_body_stomach),
            contentDescription = "body stomach",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == "stomach") Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 180.dp)
                .align(Alignment.TopCenter)
                .clickable(
                    onClick = { onClickShapePosition("stomach") },
                    interactionSource = interactionSource,
                    indication = null
                )
        )

        // 엉덩이
        Image(
            painter = painterResource(id = R.drawable.ic_body_hip),
            contentDescription = "body hip",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == "hip") Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 218.dp)
                .align(Alignment.TopCenter)
                .clickable(
                    onClick = { onClickShapePosition("hip") },
                    interactionSource = interactionSource,
                    indication = null
                )
        )

        // 다리
        Image(
            painter = painterResource(id = R.drawable.ic_body_leg_left),
            contentDescription = "body leg",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == "leg") Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 251.dp, start = 83.dp)
                .align(Alignment.TopStart)
                .clickable(
                    onClick = { onClickShapePosition("leg") },
                    interactionSource = interactionSource,
                    indication = null
                )
        )
        Image(
            painter = painterResource(id = R.drawable.ic_body_leg_right),
            contentDescription = "body leg",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == "leg") Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 251.dp, end = 84.dp)
                .align(Alignment.TopEnd)
                .clickable(
                    onClick = { onClickShapePosition("leg") },
                    interactionSource = interactionSource,
                    indication = null
                )
        )

        // 팔
        Image(
            painter = painterResource(id = R.drawable.ic_body_arm_left),
            contentDescription = "body arm",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == "arm") Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 127.dp, start = 59.dp)
                .align(Alignment.TopStart)
                .clickable(
                    onClick = { onClickShapePosition("arm") },
                    interactionSource = interactionSource,
                    indication = null
                )
        )
        Image(
            painter = painterResource(id = R.drawable.ic_body_arm_right),
            contentDescription = "body arm",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == "arm") Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 127.dp, end = 60.dp)
                .align(Alignment.TopEnd)
                .clickable(
                    onClick = { onClickShapePosition("arm") },
                    interactionSource = interactionSource,
                    indication = null
                )
        )
    }
}

@Composable
fun SelectAreaPictureBoxBack(
    selectedShapeImg: Int,
    onClickShapePosition: (String) -> Unit,
    modifier: Modifier,
    interactionSource: MutableInteractionSource,
    selectedBodyPosition: String
) {
    Box(
        modifier = modifier
            .padding(top = 35.dp)
    ) {
        Image(
            painter = painterResource(id = selectedShapeImg),
            contentDescription = "body",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(width = 234.dp, height = 351.dp)
                .clip(RoundedCornerShape(5.dp))
        )

        // 배
        Image(
            painter = painterResource(id = R.drawable.ic_body_back),
            contentDescription = "body back",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == "back") Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 156.dp)
                .align(Alignment.TopCenter)
                .clickable(
                    onClick = { onClickShapePosition("back") },
                    interactionSource = interactionSource,
                    indication = null
                )
        )

        // 엉덩이
        Image(
            painter = painterResource(id = R.drawable.ic_body_hip_back),
            contentDescription = "body hip",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == "hip") Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 194.dp)
                .align(Alignment.TopCenter)
                .clickable(
                    onClick = { onClickShapePosition("hip") },
                    interactionSource = interactionSource,
                    indication = null
                )
        )

        // 손
        Image(
            painter = painterResource(id = R.drawable.ic_body_hand_left),
            contentDescription = "body hand",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == "hand") Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 194.dp, start = 55.dp)
                .align(Alignment.TopStart)
                .clickable(
                    onClick = { onClickShapePosition("hand") },
                    interactionSource = interactionSource,
                    indication = null
                )
        )
        Image(
            painter = painterResource(id = R.drawable.ic_body_hand_right),
            contentDescription = "body hand",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == "hand") Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 196.dp, end = 61.dp)
                .align(Alignment.TopEnd)
                .clickable(
                    onClick = { onClickShapePosition("hand") },
                    interactionSource = interactionSource,
                    indication = null
                )
        )

        // 발
        Image(
            painter = painterResource(id = R.drawable.ic_body_foot_left),
            contentDescription = "body foot",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == "foot") Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 297.dp, start = 78.dp)
                .align(Alignment.TopStart)
                .clickable(
                    onClick = { onClickShapePosition("foot") },
                    interactionSource = interactionSource,
                    indication = null
                )
        )
        Image(
            painter = painterResource(id = R.drawable.ic_body_foot_right),
            contentDescription = "body foot",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == "foot") Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 297.dp, end = 82.dp)
                .align(Alignment.TopEnd)
                .clickable(
                    onClick = { onClickShapePosition("foot") },
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