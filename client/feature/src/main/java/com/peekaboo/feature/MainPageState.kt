package com.peekaboo.feature

import com.peekaboo.ui.base.PageState
import com.peekaboo.ui.common.type.BottomSheetType

data class MainPageState(
    val bottomSheetType: BottomSheetType = BottomSheetType.DEFAULT,
    val selectedLanguage: String = ""
) : PageState