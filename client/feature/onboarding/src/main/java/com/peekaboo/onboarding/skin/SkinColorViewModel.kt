package com.peekaboo.onboarding.skin

import com.peekaboo.domain.entity.request.CreateUserModel
import com.peekaboo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SkinColorViewModel @Inject constructor(

) : BaseViewModel<SkinColorPageState>(
    SkinColorPageState()
) {

    fun setUserModel(userModel: CreateUserModel) {
        updateState(
            uiState.value.copy(
                userModel = userModel
            )
        )
    }

    fun updateUserModel() = uiState.value.userModel.copy(skinType = uiState.value.selectedColor)

    fun setColor(color: String) {
        updateState(
            uiState.value.copy(
                selectedColor = color
            )
        )
    }
}