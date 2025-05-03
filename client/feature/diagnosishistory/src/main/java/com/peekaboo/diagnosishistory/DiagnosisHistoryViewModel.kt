package com.peekaboo.diagnosishistory

import com.peekaboo.domain.entity.response.DiseaseHistoryItem
import com.peekaboo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiagnosisHistoryViewModel @Inject constructor(

): BaseViewModel<DiagnosisHistoryPageState>(
    DiagnosisHistoryPageState()
) {

    init {
        initSetDiagnosisHistoryList()
    }

    private fun initSetDiagnosisHistoryList() {
        val historyList: List<DiseaseHistoryItem> = listOf(
            DiseaseHistoryItem(0,"2024.12.25","땀띠", "피부 붉은 발진, 가려움", "시원한 환경 조성", "수두", "접촉성 피부염"),
            DiseaseHistoryItem(1,"2025.01.01","땀띠", "피부 붉은 발진, 가려움", "시원한 환경", "수두", "홍역")
        )

        updateState(
            uiState.value.copy(
                diagnosisHistoryList = historyList
            )
        )
    }
}