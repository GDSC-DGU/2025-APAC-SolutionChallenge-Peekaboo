package com.peekaboo.diagnosishistory

import com.peekaboo.domain.entity.response.DiseaseHistoryItem
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryResponseModel
import com.peekaboo.ui.base.PageState

data class DiagnosisHistoryPageState (
    val diagnosisHistoryList: List<DiseaseHistoryItem> = emptyList(),
    val diagnosisHistoryList2: List<DiagnosisHistoryResponseModel.DiagnosisHistoryItem> = emptyList()
): PageState