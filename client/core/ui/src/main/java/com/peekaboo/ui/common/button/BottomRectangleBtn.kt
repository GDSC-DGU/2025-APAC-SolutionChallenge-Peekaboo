package com.peekaboo.ui.common.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Main2
import com.peekaboo.design_system.White3

@Composable
fun BottomRectangleBtn(
    horizontalPadding: Int,
    btnText: String,
) {
    BottomRectangleBtnContent(
        horizontalPadding = horizontalPadding,
        btnText = btnText
    )
}

@Composable
fun BottomRectangleBtnContent(
    horizontalPadding: Int = 0,
    btnText: String = "",
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = Main2, RoundedCornerShape(10.dp))
    ) {
        Text(
            text = btnText,
            color = White3,
            style = BaeBaeTypo.Body1,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 18.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBottomRectangleBtn() {
    BottomRectangleBtnContent()
}