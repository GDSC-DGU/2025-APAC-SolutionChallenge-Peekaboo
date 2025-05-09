package com.peekaboo.diagnosisquick.detail

import androidx.lifecycle.viewModelScope
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisConstModel
import com.peekaboo.domain.usecase.diagnosis.GetDiagnosisConstUseCase
import com.peekaboo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailQuickViewModel @Inject constructor(
    private val diagnosisConstUseCase: GetDiagnosisConstUseCase,
) : BaseViewModel<DetailQuickPageState>(
    DetailQuickPageState()
) {

    fun initSetDiagnosisModel(constId: Int) {
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
}