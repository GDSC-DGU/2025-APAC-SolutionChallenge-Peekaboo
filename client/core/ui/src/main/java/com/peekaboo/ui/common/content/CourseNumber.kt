package com.peekaboo.ui.common.content

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.CourseNumberFormat
import com.peekaboo.design_system.Main2

@SuppressLint("DefaultLocale")
@Composable
fun CourseNumber(
    currentNumber: Int,
    totalNumber: Int,
    paddingTop: Int = 0,
    paddingStart: Int = 0,
) {

    Text(
        text = String.format(CourseNumberFormat, currentNumber, totalNumber),
        color = Main2,
        style = BaeBaeTypo.Caption3,
        modifier = Modifier
            .padding(top = paddingTop.dp, start = paddingStart.dp)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCourseNumber() {
    CourseNumber(1, 5, 35, 20)
}