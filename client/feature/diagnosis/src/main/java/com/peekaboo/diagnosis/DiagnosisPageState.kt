package com.peekaboo.diagnosis

import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryDetailModel
import com.peekaboo.ui.base.PageState

data class DiagnosisPageState(
    val selectedDisease: String = "",
    val customDescription: String = "",
    val diseaseTotal: List<DiagnosisHistoryDetailModel.DiseaseDetailItem> = emptyList(),
    val firstDiseaseModel: DiagnosisHistoryDetailModel.DiseaseDetailItem = DiagnosisHistoryDetailModel.DiseaseDetailItem(),
    val secondDiseaseModel: DiagnosisHistoryDetailModel.DiseaseDetailItem = DiagnosisHistoryDetailModel.DiseaseDetailItem(),
    val thirdDiseaseModel: DiagnosisHistoryDetailModel.DiseaseDetailItem = DiagnosisHistoryDetailModel.DiseaseDetailItem()
) : PageState