package com.peekaboo.onboarding.personal

import com.peekaboo.domain.entity.request.CreateUserModel
import com.peekaboo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class PersonalInputViewmodel @Inject constructor(

) : BaseViewModel<PersonalInputPageState>(
    PersonalInputPageState()
) {

    fun setUserModel(userModel: CreateUserModel) {
        updateState(
            uiState.value.copy(
                userModel = userModel
            )
        )
    }

    fun updateUserModel() = uiState.value.userModel.copy(birth = uiState.value.birthInput, sex = uiState.value.selectedSex, bloodType = uiState.value.bloodType)

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

    fun onBirthValueChange(newValue: String) {
        updateState(
            uiState.value.copy(
                birthInput = newValue
            )
        )
    }

    fun isBirthValid(): Boolean {
        val birthMatcher = BIRTH_PATTERN.matcher(uiState.value.birthInput)
        return birthMatcher.find()
    }

    companion object {
        private const val BIRTH_REGEX = "^\\d{4}\\.(0[1-9]|1[0-2])\\.(0[1-9]|[12][0-9]|3[01])\$"
        val BIRTH_PATTERN: Pattern = Pattern.compile(BIRTH_REGEX)
    }
}