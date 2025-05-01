package com.peekaboo.ui.common.item

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
import com.peekaboo.design_system.Gray1
import com.peekaboo.design_system.Gray3
import com.peekaboo.design_system.Main2
import com.peekaboo.design_system.White3

@Composable
fun SelectItem(
    itemText: String,
) {
    SelectItemContent(
        itemText = itemText
    )
}

@Composable
fun SelectItemContent(
    itemText: String = "",
    isItemSelected: Boolean = false,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(color = if (isItemSelected) Main2 else Gray1, RoundedCornerShape(5.dp))
    ) {
        Text(
            text = itemText,
            color = if (isItemSelected) White3 else Gray3,
            style = BaeBaeTypo.Body3,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 13.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSelectItem() {
    SelectItemContent()
}