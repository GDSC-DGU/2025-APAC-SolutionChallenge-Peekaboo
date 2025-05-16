package com.peekaboo.diagnosisquick.detail

import androidx.lifecycle.viewModelScope
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisConstModel
import com.peekaboo.domain.usecase.diagnosis.GetDiagnosisConstUseCase
import com.peekaboo.ui.base.BaseViewModel
import com.peekaboo.ui.common.type.DiseaseType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailQuickViewModel @Inject constructor(
    private val diagnosisConstUseCase: GetDiagnosisConstUseCase,
) : BaseViewModel<DetailQuickPageState>(
    DetailQuickPageState()
) {

    fun initGetDiagnosisConstId(constId: Int) {
        initSetDiagnosisModel(constId)
        initSetDiagnosisImg(constId)
    }

    private fun initSetDiagnosisModel(constId: Int) {
        viewModelScope.launch {
            diagnosisConstUseCase(request = constId).collect {
                resultResponse(it, ::onSuccessDiagnosisConstModel)
            }
        }
    }

    private fun onSuccessDiagnosisConstModel(data: DiagnosisConstModel) {
        updateState(
            uiState.value.copy(
                diagnosisModel = data
            )
        )
    }

    private fun initSetDiagnosisImg(constId: Int) {
        updateState(
            uiState.value.copy(
                diagnosisImg = DiseaseType.getDiagnosisImg(constId)
            )
        )
    }
}