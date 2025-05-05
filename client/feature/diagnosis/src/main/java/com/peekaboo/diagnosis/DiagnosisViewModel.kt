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
    private val getDiagnosisHistoryDetailUseCase: GetDiagnosisHistoryDetailUseCase,
) : BaseViewModel<DiagnosisPageState>(
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
        val firstDisease = matchDiseaseNumber(1, data)
        val secondDisease = matchDiseaseNumber(2, data)
        val thirdDisease = matchDiseaseNumber(3, data)

        updateState(
            uiState.value.copy(
                customDescription = data.customDescription,
                selectedDisease = firstDisease.diseaseName,
                diseaseTotal = data.diseaseList,
                firstDiseaseModel = firstDisease,
                secondDiseaseModel = secondDisease,
                thirdDiseaseModel = thirdDisease
            )
        )
    }

    private fun matchDiseaseNumber(ranking: Int, data: DiagnosisHistoryDetailModel) =
        data.diseaseList.firstOrNull { it.ranking == ranking }
            ?: DiagnosisHistoryDetailModel.DiseaseDetailItem()
}