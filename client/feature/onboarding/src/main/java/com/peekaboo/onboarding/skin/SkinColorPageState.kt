package com.peekaboo.onboarding.skin

import androidx.compose.ui.graphics.Color
import com.peekaboo.ui.base.PageState

data class SkinColorPageState (
    val skinColorList: List<Color> = emptyList()
): PageState