package com.peekaboo.ui.common.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.CourseNumberFormat
import com.peekaboo.design_system.Gray2
import com.peekaboo.design_system.Gray3
import com.peekaboo.design_system.Main2
import com.peekaboo.design_system.R
import com.peekaboo.design_system.White2

@Composable
fun TextFieldBox(
    textInput: String,
    hintText: String = "",
    onValueChange: (String) -> Unit,
    horizontalPadding: Int,
    isDeleteBtnValid: Boolean = false,
    onClickDeleteBtn: () -> Unit = {},
    height: Int = 0,
    textMaxLength: Int = 0
) {
    TextFieldContent(
        textInput = textInput,
        hintText = hintText,
        onValueChange = onValueChange,
        horizontalPadding = horizontalPadding,
        isDeleteBtnValid = isDeleteBtnValid,
        onClickDeleteBtn = onClickDeleteBtn,
        height = height,
        textMaxLength = textMaxLength
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TextFieldContent(
    textInput: String = "",
    hintText: String = "",
    onValueChange: (String) -> Unit = {},
    horizontalPadding: Int = 0,
    isDeleteBtnValid: Boolean = false,
    onClickDeleteBtn: () -> Unit = {},
    height: Int = 0,
    textMaxLength: Int = 0
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val imeVisible = WindowInsets.isImeVisible

    LaunchedEffect(imeVisible) {
        if (!imeVisible) {
            focusManager.clearFocus()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
//                .padding(horizontal = horizontalPadding.dp)
                .then(
                    if (height == 0) Modifier.wrapContentHeight() else Modifier.height(height.dp)
                )
                .clip(RoundedCornerShape(5.dp))
                .border(1.dp, color = Gray2, RoundedCornerShape(5.dp))
                .background(White2)
        ) {
            BasicTextField(
                value = textInput,
                onValueChange = { input ->
                    onValueChange(input)
                },
                modifier = Modifier
                    .padding(horizontal = 15.dp, vertical = 21.dp)
                    .weight(1f)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    },
                textStyle = BaeBaeTypo.Caption4.copy(
                    color = Black1
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        if (textInput.isEmpty() && !isFocused) {
                            Text(
                                text = hintText,
                                style = BaeBaeTypo.Caption1,
                                color = Gray3
                            )
                        }
                        innerTextField()
                    }
                }
            )

            if (isDeleteBtnValid) {
                Image(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = "close",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(5.dp)
                        .size(20.dp)
                        .clickable(
                            onClick = onClickDeleteBtn
                        )
                )

                Spacer(modifier = Modifier.width(10.dp))
            }
        }

        Text(
            text = String.format(CourseNumberFormat, textInput.length, textMaxLength),
            color = if (textInput.isNotEmpty()) Main2 else Gray3,
            style = BaeBaeTypo.Caption2,
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 5.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewTextField() {
    TextFieldContent()
}