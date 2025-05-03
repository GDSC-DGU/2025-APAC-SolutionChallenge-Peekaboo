package com.peekaboo.diagnosishistory

import com.peekaboo.domain.entity.response.DiseaseHistoryItem
import com.peekaboo.ui.base.PageState

data class DiagnosisHistoryPageState (
    val diagnosisHistoryList: List<DiseaseHistoryItem> = emptyList()
): PageState