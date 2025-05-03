package com.peekaboo.ui.common.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Gray3
import com.peekaboo.design_system.R
import com.peekaboo.design_system.White2

@Composable
fun CautionNoticeBox(
    horizontalPadding: Int = 0,
    cautionText: String = "",
) {
    CautionNoticeContent(
        horizontalPadding = horizontalPadding,
        cautionText = cautionText
    )
}

@Composable
fun CautionNoticeContent(
    horizontalPadding: Int = 0,
    cautionText: String = "",
) {
    Row(
        modifier = Modifier
            .padding(horizontal = horizontalPadding.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(White2)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_caution),
            contentDescription = "caution",
            modifier = Modifier
                .padding(vertical = 15.dp)
                .padding(start = 15.dp)
                .size(15.dp)
        )

        Text(
            text = cautionText,
            color = Gray3,
            style = BaeBaeTypo.Caption1,
            modifier = Modifier
                .padding(start = 3.dp, end = 15.dp)
                .padding(vertical = 16.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCautionNotice() {
    CautionNoticeContent()
}