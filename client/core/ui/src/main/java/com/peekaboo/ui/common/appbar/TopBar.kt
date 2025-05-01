package com.peekaboo.ui.common.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.R

@Composable
fun TopBar(
    titleText: String,
    isIconValid: Boolean = false
) {

    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = titleText,
            color = Black1,
            style = BaeBaeTypo.Head1,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 20.dp)
        )

        if (isIconValid) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_before),
                contentDescription = "arrow before",
                modifier = Modifier
                    .padding(15.dp)
                    .size(20.dp)
                    .align(Alignment.CenterStart)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewTopBar() {
    TopBar("타이틀")
}