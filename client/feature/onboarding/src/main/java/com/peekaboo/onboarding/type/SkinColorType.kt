package com.peekaboo.onboarding.type

import androidx.compose.ui.graphics.Color
import com.peekaboo.design_system.Fawn
import com.peekaboo.design_system.MellowApricot
import com.peekaboo.design_system.NavajoWhite
import com.peekaboo.design_system.Peru
import com.peekaboo.design_system.Russet

enum class SkinColorType(
    val color: Color,
    val apiType: String,
) {
    NAVAJOWHITE(NavajoWhite, "NavajoWhite"),
    MELLOWAPRICOT(MellowApricot, "MellowApricot"),
    FAWN(Fawn, "Fawn"),
    PERU(Peru, "Peru"),
    RUSSET(Russet, "Russet");
}