package com.peekaboo.diagnosis.selectarea

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@Composable
fun SelectAreaScreen(
    goToPicturePage: (DiagnosisModel) -> Unit,
) {

    val viewModel: SelectAreaViewModel = hiltViewModel()
    val uiState: SelectAreaPageState by viewModel.uiState.collectAsStateWithLifecycle()

    SelectAreaContent(
        onClickNextBtn = { goToPicturePage(viewModel.updateDiagnosisContent()) },
        onSelectShapeChip = { viewModel.setSelectedShape(it) },
        selectedShape = uiState.selectedShape,
        selectedShapeImg = uiState.selectedShapeImg
    )
}

@Composable
fun SelectAreaContent(
    onClickNextBtn: () -> Unit = {},
    onSelectShapeChip: (String) -> Unit = {},
    selectedShape: String = "",
    selectedShapeImg: Int = 0,
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
                selectedShapeImg = selectedShapeImg
            )
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
) {
    Image(
        painter = painterResource(id = selectedShapeImg),
        contentDescription = "body",
        modifier = Modifier
            .padding(top = 35.dp, bottom = 54.dp, start = 63.dp, end = 63.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(5.dp))
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSelectArea() {
    SelectAreaContent()
}