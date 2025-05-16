package com.peekaboo.diagnosishistory

import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryResponseModel
import com.peekaboo.ui.base.PageState

data class DiagnosisHistoryPageState(
    val diagnosisHistoryList: List<DiagnosisHistoryResponseModel.DiagnosisHistoryItem> = emptyList(),
    val isDataUpdated: Boolean = false
) : PageState