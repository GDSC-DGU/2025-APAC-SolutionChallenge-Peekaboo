package com.peekaboo.diagnosishistory

import androidx.lifecycle.viewModelScope
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryResponseModel
import com.peekaboo.domain.usecase.diagnosis.GetDiagnosisHistoryUseCase
import com.peekaboo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiagnosisHistoryViewModel @Inject constructor(
    private val getDiagnosisHistoryUseCase: GetDiagnosisHistoryUseCase,
) : BaseViewModel<DiagnosisHistoryPageState>(
    DiagnosisHistoryPageState()
) {

    init {
        initSetDiagnosisHistoryList()
    }

    private fun initSetDiagnosisHistoryList() {
        viewModelScope.launch {
            getDiagnosisHistoryUseCase(request = Unit).collect {
                resultResponse(it, ::onSuccessDiagnosisHistory)
            }
        }
    }

    private fun onSuccessDiagnosisHistory(data: DiagnosisHistoryResponseModel) {
        updateState(
            uiState.value.copy(
                diagnosisHistoryList = data.historyList
            )
        )
    }
}