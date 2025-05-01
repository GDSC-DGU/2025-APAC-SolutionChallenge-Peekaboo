package com.peekaboo.onboarding.personal

import com.peekaboo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonalInputViewmodel @Inject constructor(

): BaseViewModel<PersonalInputPageState>(
    PersonalInputPageState()
) {

    fun setSelectedSex(sex: String) {
        updateState(
            uiState.value.copy(
                selectedSex = sex
            )
        )
    }

    fun setBloodType(blood: String) {
        updateState(
            uiState.value.copy(
                bloodType = blood
            )
        )
    }
}