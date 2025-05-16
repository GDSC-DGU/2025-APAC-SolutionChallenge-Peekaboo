package com.peekaboo.design_system

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val fontFamily = FontFamily(
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.omyu_pretty, FontWeight.Normal)
)

object BaeBaeTypo {
    val Head1: TextStyle = TextStyle(
        fontSize = 20.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold
    )
    val Head2: TextStyle = TextStyle(
        fontSize = 20.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium
    )
    val Head3: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold
    )
    val Head4: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium
    )

    val Body1: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold
    )
    val Body2: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium
    )
    val Body3: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold
    )
    val Body4: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium
    )

    val Caption1: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold
    )
    val Caption2: TextStyle = TextStyle(
        fontSize = 10.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold
    )
    val Caption3: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal
    )
    val Caption4: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal
    )
    val Caption5: TextStyle = TextStyle(
        fontSize = 8.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal
    )
}