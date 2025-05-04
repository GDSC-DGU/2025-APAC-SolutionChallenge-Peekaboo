package com.peekaboo.diagnosis.explain

import com.peekaboo.domain.entity.request.DiagnosisModel
import com.peekaboo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SymptomExplainViewModel @Inject constructor(

) : BaseViewModel<SymptomExplainPageState>(
    SymptomExplainPageState()
) {

    fun setDiagnosisContent(diagnosis: DiagnosisModel) {
        updateState(
            uiState.value.copy(
                diagnosisContent = diagnosis
            )
        )
    }

    fun onExplainValueChange(newValue: String) {
        updateState(
            uiState.value.copy(
                explainInput = newValue
            )
        )
    }

    fun updateDiagnosisContent() =
        uiState.value.diagnosisContent.copy(symptomsExplain = uiState.value.explainInput)

    fun diagnoseSymptom() {
        // TODO 서버통신

        Timber.d("[테스트] -> ${uiState.value.diagnosisContent}")
        emitEventFlow(SymptomExplainEvent.GoToDiagnosisResultPage)
    }
}