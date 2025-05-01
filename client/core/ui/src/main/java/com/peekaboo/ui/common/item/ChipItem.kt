package com.peekaboo.ui.common.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Gray1
import com.peekaboo.design_system.Gray3

@Composable
fun ChipItem(
    chipText: String,
) {
    ChipContent(
        chipText = chipText
    )
}

@Composable
fun ChipContent(
    chipText: String = "",
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(Gray1)
    ) {
        Text(
            text = chipText,
            color = Gray3,
            style = BaeBaeTypo.Caption4,
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 12.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewChip() {
    ChipContent()
}