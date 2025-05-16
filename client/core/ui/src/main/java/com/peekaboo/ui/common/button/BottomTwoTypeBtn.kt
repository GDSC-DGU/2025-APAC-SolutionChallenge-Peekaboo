package com.peekaboo.ui.common.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Gray1
import com.peekaboo.design_system.Gray3
import com.peekaboo.design_system.Main2
import com.peekaboo.design_system.White3

@Composable
fun BottomTwoTypeBtn(
    modifier: Modifier = Modifier,
    itemText: String = "",
    isItemPositive: Boolean = false,
    onClickAction: () -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }

    BottomTwoTypeContent(
        modifier = modifier,
        interactionSource = interactionSource,
        itemText = itemText,
        isItemPositive = isItemPositive,
        onClickAction = onClickAction
    )
}

@Composable
fun BottomTwoTypeContent(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    itemText: String = "",
    isItemPositive: Boolean = false,
    onClickAction: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(color = if (isItemPositive) Main2 else Gray1, RoundedCornerShape(5.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClickAction
            )
    ) {
        Text(
            text = itemText,
            color = if (isItemPositive) White3 else Gray3,
            style = BaeBaeTypo.Body3,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 13.dp)
        )
    }
}