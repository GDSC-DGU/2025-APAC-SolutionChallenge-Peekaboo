package com.peekaboo.feature

import com.peekaboo.domain.entity.request.CreateUserModel
import com.peekaboo.domain.entity.request.DiagnosisModel
import com.peekaboo.ui.base.BaseViewModel
import com.peekaboo.ui.common.type.BottomSheetType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

): BaseViewModel<MainPageState>(
    MainPageState()
) {

    val userModel = MutableSharedFlow<CreateUserModel>(replay = 1)
    val diagnosisContent = MutableSharedFlow<DiagnosisModel>(replay = 1)
    val selectedDiagnosisHistory = MutableSharedFlow<Int>(replay = 1)
    val diagnosisConstId = MutableSharedFlow<Int>(replay = 1)
    val finalSelectedLanguage = MutableSharedFlow<String>(replay = 1)

    fun setLanguageSelect() {
        updateState(
            uiState.value.copy(
                bottomSheetType = BottomSheetType.LANGUAGE
            )
        )
    }

    fun updateSelectedLanguage(language: String) {
        updateState(
            uiState.value.copy(
                selectedLanguage = language
            )
        )
    }
}