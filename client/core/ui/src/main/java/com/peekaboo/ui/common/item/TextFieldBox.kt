package com.peekaboo.ui.common.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.Gray2
import com.peekaboo.design_system.Gray3
import com.peekaboo.design_system.White2

@Composable
fun TextFieldBox(
    textInput: String,
    hintText: String = "",
    onValueChange: (String) -> Unit,
    horizontalPadding: Int,
) {
    TextFieldContent(
        textInput = textInput,
        hintText = hintText,
        onValueChange = onValueChange,
        horizontalPadding = horizontalPadding
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TextFieldContent(
    textInput: String = "",
    hintText: String = "",
    onValueChange: (String) -> Unit = {},
    horizontalPadding: Int = 0,
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val imeVisible = WindowInsets.isImeVisible

    LaunchedEffect(imeVisible) {
        if (!imeVisible) {
            focusManager.clearFocus()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding.dp)
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
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 21.dp)
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
                        .fillMaxWidth(),
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
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewTextField() {
    TextFieldContent()
}