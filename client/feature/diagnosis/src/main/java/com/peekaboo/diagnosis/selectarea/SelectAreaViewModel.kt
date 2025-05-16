package com.peekaboo.diagnosis.selectarea

import com.peekaboo.design_system.DiagnosisSelectAreaBack
import com.peekaboo.design_system.DiagnosisSelectAreaFront
import com.peekaboo.design_system.R
import com.peekaboo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectAreaViewModel @Inject constructor(

) : BaseViewModel<SelectAreaPageState>(
    SelectAreaPageState()
) {

    fun setSelectedShape(shape: String) {
        var selectedImg = R.drawable.ic_body_front

        when (shape) {
            DiagnosisSelectAreaFront -> {
                selectedImg = R.drawable.ic_body_front
            }

            DiagnosisSelectAreaBack -> {
                selectedImg = R.drawable.ic_body_back_img
            }
        }
        updateState(
            uiState.value.copy(
                selectedShape = shape,
                selectedShapeImg = selectedImg
            )
        )
    }

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