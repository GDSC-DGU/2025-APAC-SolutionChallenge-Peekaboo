package com.peekaboo.diagnosis

import com.peekaboo.domain.entity.response.diagnosis.DiagnosisConstModel
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryDetailModel
import com.peekaboo.ui.base.PageState

data class DiagnosisPageState(
    val diagnosisId: Int = 0,
    val selectedDisease: String = "",
    val customDescription: String = "",
    val diseaseTotal: List<DiagnosisHistoryDetailModel.DiseaseDetailItem> = emptyList(),
    val firstDiseaseModel: DiagnosisHistoryDetailModel.DiseaseDetailItem = DiagnosisHistoryDetailModel.DiseaseDetailItem(),
    val secondDiseaseModel: DiagnosisHistoryDetailModel.DiseaseDetailItem = DiagnosisHistoryDetailModel.DiseaseDetailItem(),
    val thirdDiseaseModel: DiagnosisHistoryDetailModel.DiseaseDetailItem = DiagnosisHistoryDetailModel.DiseaseDetailItem(),
    val diagnosisModel: DiagnosisConstModel = DiagnosisConstModel(),
    val isDataUpdateSuccess: Boolean = false
) : PageState