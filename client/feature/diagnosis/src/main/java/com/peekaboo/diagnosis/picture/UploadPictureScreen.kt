package com.peekaboo.diagnosis.picture

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.DiagnosisSelectPicture
import com.peekaboo.design_system.DiagnosisTitle
import com.peekaboo.design_system.Gray2
import com.peekaboo.design_system.Next
import com.peekaboo.design_system.R
import com.peekaboo.design_system.White3
import com.peekaboo.domain.entity.request.DiagnosisModel
import com.peekaboo.ui.common.appbar.TopBar
import com.peekaboo.ui.common.button.BottomRectangleBtn
import com.peekaboo.ui.common.content.CourseNumber
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun UploadPictureScreen(
    diagnosisModel: SharedFlow<DiagnosisModel>,
    goToExplainPage: (DiagnosisModel) -> Unit,
    onClickBackBtn: () -> Unit
) {
    val viewModel: UploadPictureViewModel = hiltViewModel()
    val uiState: UploadPicturePageState by viewModel.uiState.collectAsStateWithLifecycle()

    val interactionSource = remember { MutableInteractionSource() }
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            imageUri = uri
            viewModel.setSelectedImg(context, uri)
        }
    )
    val bitmap = imageUri?.let {
        if (Build.VERSION.SDK_INT < 28) {
            MediaStore.Images.Media.getBitmap(context.contentResolver, it)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, it)
            ImageDecoder.decodeBitmap(source)
        }
    }

    LaunchedEffect(diagnosisModel) {
        diagnosisModel.collect {
            viewModel.setDiagnosisContent(it)
        }
    }

    UploadPictureContent(
        onClickNextBtn = { goToExplainPage(viewModel.updateDiagnosisContent()) },
        onClickPlusBtn = { launcher.launch("image/*") },
        selectedImg = bitmap,
        onClickDeleteBtn = {
            imageUri = null
            viewModel.setSelectedImg(context, null)
        },
        interactionSource = interactionSource,
        onClickBackBtn = onClickBackBtn
    )
}

@Composable
fun UploadPictureContent(
    onClickNextBtn: () -> Unit = {},
    onClickPlusBtn: () -> Unit = {},
    onClickDeleteBtn: () -> Unit = {},
    selectedImg: Bitmap? = null,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
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
                currentNumber = 2,
                totalNumber = 3,
                paddingTop = 35,
                paddingStart = 20
            )

            Text(
                text = DiagnosisSelectPicture,
                color = Black1,
                style = BaeBaeTypo.Body1,
                modifier = Modifier
                    .padding(top = 10.dp, start = 20.dp)
            )

            UploadPictureBox(
                interactionSource = interactionSource,
                onClickPlusBtn = onClickPlusBtn,
                selectedImg = selectedImg,
                onClickDeleteBtn = onClickDeleteBtn
            )
        }

        BottomRectangleBtn(
            horizontalPadding = 20,
            btnText = Next,
            isBtnValid = (selectedImg != null),
            onClickAction = onClickNextBtn
        )

        Spacer(modifier = Modifier.height(36.dp))
    }
}

@Composable
fun UploadPictureBox(
    interactionSource: MutableInteractionSource,
    onClickPlusBtn: () -> Unit,
    onClickDeleteBtn: () -> Unit,
    selectedImg: Bitmap?,
) {
    Box(
        modifier = Modifier
            .padding(top = 68.dp, bottom = 111.dp, start = 50.dp, end = 50.dp)
            .fillMaxSize()
            .border(1.dp, color = Gray2, RoundedCornerShape(12.dp))
            .clickable(
                onClick = onClickPlusBtn,
                interactionSource = interactionSource,
                indication = null
            )
    ) {
        if (selectedImg == null) {
            Image(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "add",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(32.dp)
            )
        } else {
            Image(
                bitmap = selectedImg.asImageBitmap(),
                contentDescription = "selected image",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Image(
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = "delete",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 10.dp, end = 10.dp)
                    .clickable(
                        onClick = onClickDeleteBtn,
                        interactionSource = interactionSource,
                        indication = null
                    )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewUploadPicture() {
    UploadPictureContent()
}