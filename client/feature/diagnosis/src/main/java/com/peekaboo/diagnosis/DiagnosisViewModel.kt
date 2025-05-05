package com.peekaboo.diagnosis

import androidx.lifecycle.viewModelScope
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryDetailModel
import com.peekaboo.domain.usecase.diagnosis.GetDiagnosisHistoryDetailUseCase
import com.peekaboo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiagnosisViewModel @Inject constructor(
    private val getDiagnosisHistoryDetailUseCase: GetDiagnosisHistoryDetailUseCase
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

    fun setDiagnosisResult(historyId: Int) {
        viewModelScope.launch {
            getDiagnosisHistoryDetailUseCase(request = historyId).collect {
                resultResponse(it, ::onSuccessDiagnosisHistoryDetail)
            }
        }
    }

    private fun onSuccessDiagnosisHistoryDetail(data: DiagnosisHistoryDetailModel) {
        updateState(
            uiState.value.copy(
                customDescription = data.customDescription,
                diseaseList = data.diseaseList
            )
        )
    }
}