package com.peekaboo.onboarding.skin

import androidx.compose.ui.graphics.Color
import com.peekaboo.design_system.Fawn
import com.peekaboo.design_system.MellowApricot
import com.peekaboo.design_system.NavajoWhite
import com.peekaboo.design_system.Peru
import com.peekaboo.design_system.Russet
import com.peekaboo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SkinColorViewModel @Inject constructor(

) : BaseViewModel<SkinColorPageState>(
    SkinColorPageState()
) {
    init {
        initSetSkinColorList()
    }

    private fun initSetSkinColorList() {
        val colors: List<Color> = listOf(
            NavajoWhite, MellowApricot, Fawn, Peru, Russet
        )

        updateState(
            uiState.value.copy(
                skinColorList = colors
            )
        )
    }
}