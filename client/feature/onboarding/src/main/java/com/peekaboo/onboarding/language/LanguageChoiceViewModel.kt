package com.peekaboo.onboarding.language

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
}