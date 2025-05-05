package com.peekaboo.diagnosis

import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryDetailModel
import com.peekaboo.ui.base.PageState

data class DiagnosisPageState(
    val selectedDisease: String = "접촉성 피부염",
    val customDescription: String = "",
    val diseaseList: List<DiagnosisHistoryDetailModel.DiseaseDetailItem> = emptyList(),
) : PageState