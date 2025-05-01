package com.peekaboo.onboarding.language

import com.peekaboo.domain.entity.request.CreateUserModel
import com.peekaboo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LanguageChoiceViewModel @Inject constructor(

) : BaseViewModel<LanguageChoicePageState>(
    LanguageChoicePageState()
) {

    fun setSelectedLocation(location: String) {
        updateState(
            uiState.value.copy(
                selectedLocation = location
            )
        )
    }

    fun setSelectedLanguage(language: String) {
        updateState(
            uiState.value.copy(
                selectedLanguage = language
            )
        )
    }

    fun setUserModel() = CreateUserModel(
        location = uiState.value.selectedLocation,
        language = uiState.value.selectedLanguage
    )
}