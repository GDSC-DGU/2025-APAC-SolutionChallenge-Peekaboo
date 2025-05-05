package com.peekaboo.data.entity.response.dianosis

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiagnosisHistoryResponseDto(
    @SerialName("historyList")
    val historyList: List<DiagnosisHistoryItem> = emptyList(),
) {
    @Serializable
    data class DiagnosisHistoryItem(
        @SerialName("diagnosisId")
        val diagnosisId: Int = 0,
        @SerialName("customDescription")
        val customDescription: String = "",
        @SerialName("createAt")
        val createAt: String = "",
        @SerialName("diseaseList")
        val diseaseList: List<DiseaseItem> = emptyList(),
    ) {
        @Serializable
        data class DiseaseItem(
            @SerialName("diseaseId")
            val diseaseId: Int = 0,
            @SerialName("diseaseName")
            val diseaseName: String = "",
            @SerialName("ranking")
            val ranking: Int = 0,
        )
    }
}