package com.peekaboo.diagnosis

import com.peekaboo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiagnosisViewModel @Inject constructor(

): BaseViewModel<DiagnosisPageState>(
    DiagnosisPageState()
) {

    fun setSelectedDisease(disease: String) {
        updateState(
            uiState.value.copy(
                selectedDisease = disease
            )
        )
    }
}