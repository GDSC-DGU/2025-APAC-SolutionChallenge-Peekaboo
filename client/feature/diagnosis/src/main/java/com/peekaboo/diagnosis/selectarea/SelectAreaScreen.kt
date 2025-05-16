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
import com.peekaboo.diagnosis.type.SelectAreaType
import com.peekaboo.domain.entity.request.DiagnosisModel
import com.peekaboo.ui.common.appbar.TopBar
import com.peekaboo.ui.common.button.BottomRectangleBtn
import com.peekaboo.ui.common.content.CourseNumber
import com.peekaboo.ui.common.item.ChipItem

@Composable
fun SelectAreaScreen(
    goToPicturePage: (DiagnosisModel) -> Unit,
    onClickBackBtn: () -> Unit
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
        selectedArea = uiState.selectedArea,
        onClickBackBtn = onClickBackBtn
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
    onClickBackBtn: () -> Unit = {}
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
                isIconValid = true,
                onClickIcon = onClickBackBtn
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
            isBtnValid = selectedArea.isNotEmpty(),
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
            painter = painterResource(id = SelectAreaType.HEAD.bodyImg),
            contentDescription = "body head",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == SelectAreaType.HEAD.position) Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 13.dp)
                .align(Alignment.TopCenter)
                .clickable(
                    onClick = { onClickShapePosition(SelectAreaType.HEAD.position) },
                    interactionSource = interactionSource,
                    indication = null
                )
        )

        //얼굴
        Image(
            painter = painterResource(id = SelectAreaType.FACE.bodyImg),
            contentDescription = "body face",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == SelectAreaType.FACE.position) Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 56.dp)
                .align(Alignment.TopCenter)
                .clickable(
                    onClick = { onClickShapePosition(SelectAreaType.FACE.position) },
                    interactionSource = interactionSource,
                    indication = null
                )
        )

        // 배
        Image(
            painter = painterResource(id = SelectAreaType.STOMACH.bodyImg),
            contentDescription = "body stomach",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == SelectAreaType.STOMACH.position) Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 142.dp)
                .align(Alignment.TopCenter)
                .clickable(
                    onClick = { onClickShapePosition(SelectAreaType.STOMACH.position) },
                    interactionSource = interactionSource,
                    indication = null
                )
        )

        // 다리
        Image(
            painter = painterResource(id = SelectAreaType.LEGLEFT.bodyImg),
            contentDescription = "body leg",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == SelectAreaType.LEGLEFT.position) Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 247.dp, start = 83.dp)
                .align(Alignment.TopStart)
                .clickable(
                    onClick = { onClickShapePosition(SelectAreaType.LEGLEFT.position) },
                    interactionSource = interactionSource,
                    indication = null
                )
        )
        Image(
            painter = painterResource(id = SelectAreaType.LEGRIGHT.bodyImg),
            contentDescription = "body leg",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == SelectAreaType.LEGRIGHT.position) Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 248.dp, end = 84.dp)
                .align(Alignment.TopEnd)
                .clickable(
                    onClick = { onClickShapePosition(SelectAreaType.LEGRIGHT.position) },
                    interactionSource = interactionSource,
                    indication = null
                )
        )

        // 팔
        Image(
            painter = painterResource(id = SelectAreaType.ARMLEFT.bodyImg),
            contentDescription = "body arm",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == SelectAreaType.ARMLEFT.position) Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 127.dp, start = 59.dp)
                .align(Alignment.TopStart)
                .clickable(
                    onClick = { onClickShapePosition(SelectAreaType.ARMLEFT.position) },
                    interactionSource = interactionSource,
                    indication = null
                )
        )
        Image(
            painter = painterResource(id = SelectAreaType.ARMRIGHT.bodyImg),
            contentDescription = "body arm",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == SelectAreaType.ARMRIGHT.position) Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 127.dp, end = 60.dp)
                .align(Alignment.TopEnd)
                .clickable(
                    onClick = { onClickShapePosition(SelectAreaType.ARMRIGHT.position) },
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

        // 등
        Image(
            painter = painterResource(id = SelectAreaType.BACK.bodyImg),
            contentDescription = "body back",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == SelectAreaType.BACK.position) Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 119.dp, start = 77.dp, end = 81.dp)
                .align(Alignment.TopCenter)
                .clickable(
                    onClick = { onClickShapePosition(SelectAreaType.BACK.position) },
                    interactionSource = interactionSource,
                    indication = null
                )
        )

        // 엉덩이
        Image(
            painter = painterResource(id = SelectAreaType.HIP.bodyImg),
            contentDescription = "body hip",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == SelectAreaType.HIP.position) Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 192.dp, start = 78.dp, end = 82.dp)
                .align(Alignment.TopCenter)
                .clickable(
                    onClick = { onClickShapePosition(SelectAreaType.HIP.position) },
                    interactionSource = interactionSource,
                    indication = null
                )
        )

        // 손
        Image(
            painter = painterResource(id = SelectAreaType.HANDLEFT.bodyImg),
            contentDescription = "body hand",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == SelectAreaType.HANDLEFT.position) Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 194.dp, start = 55.dp)
                .align(Alignment.TopStart)
                .clickable(
                    onClick = { onClickShapePosition(SelectAreaType.HANDLEFT.position) },
                    interactionSource = interactionSource,
                    indication = null
                )
        )
        Image(
            painter = painterResource(id = SelectAreaType.HANDRIGHT.bodyImg),
            contentDescription = "body hand",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == SelectAreaType.HANDRIGHT.position) Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 196.dp, end = 61.dp)
                .align(Alignment.TopEnd)
                .clickable(
                    onClick = { onClickShapePosition(SelectAreaType.HANDRIGHT.position) },
                    interactionSource = interactionSource,
                    indication = null
                )
        )

        // 발
        Image(
            painter = painterResource(id = SelectAreaType.FOOTLEFT.bodyImg),
            contentDescription = "body foot",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == SelectAreaType.FOOTLEFT.position) Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 297.dp, start = 78.dp)
                .align(Alignment.TopStart)
                .clickable(
                    onClick = { onClickShapePosition(SelectAreaType.FOOTLEFT.position) },
                    interactionSource = interactionSource,
                    indication = null
                )
        )
        Image(
            painter = painterResource(id = SelectAreaType.FOOTRIGHT.bodyImg),
            contentDescription = "body foot",
            colorFilter = ColorFilter.tint(if (selectedBodyPosition == SelectAreaType.FOOTRIGHT.position) Main2 else Color.Transparent),
            modifier = Modifier
                .padding(top = 297.dp, end = 82.dp)
                .align(Alignment.TopEnd)
                .clickable(
                    onClick = { onClickShapePosition(SelectAreaType.FOOTRIGHT.position) },
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