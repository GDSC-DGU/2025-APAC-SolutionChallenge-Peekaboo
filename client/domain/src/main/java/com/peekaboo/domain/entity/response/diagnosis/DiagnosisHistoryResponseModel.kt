package com.peekaboo.domain.entity.response.diagnosis

data class DiagnosisHistoryResponseModel(
    val historyList: List<DiagnosisHistoryItem> = emptyList(),
) {
    data class DiagnosisHistoryItem(
        val diagnosisId: Int = 0,
        val customDescription: String = "",
        val createAt: String = "",
        val diseaseList: List<DiseaseItem> = emptyList(),
    ) {
        data class DiseaseItem(
            val diseaseId: Int = 0,
            val diseaseName: String = "",
            val ranking: Int = 0,
        )
    }
}