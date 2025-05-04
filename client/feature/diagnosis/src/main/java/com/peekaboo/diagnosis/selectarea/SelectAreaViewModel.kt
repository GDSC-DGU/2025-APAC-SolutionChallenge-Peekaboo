package com.peekaboo.diagnosis.selectarea

import com.peekaboo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectAreaViewModel @Inject constructor(

) : BaseViewModel<SelectAreaPageState>(
    SelectAreaPageState()
) {

    fun setSelectedArea(area: String) {
        updateState(
            uiState.value.copy(
                selectedArea = area
            )
        )
    }

    fun updateDiagnosisContent() =
        uiState.value.diagnosisContent.copy(selectedArea = uiState.value.selectedArea)
}