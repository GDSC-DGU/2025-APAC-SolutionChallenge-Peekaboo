package com.peekaboo.data.entity.response.dianosis

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiagnosisHistoryDetailResponseDto(
    @SerialName("customDescription")
    val customDescription: String = "",
    @SerialName("diagnosisId")
    val diagnosisId: Int = 0,
    @SerialName("diseaseList")
    val diseaseList: List<DiseaseDetailItem> = emptyList(),
) {
    @Serializable
    data class DiseaseDetailItem(
        @SerialName("diseaseId")
        val diseaseId: Int = 0,
        @SerialName("diseaseName")
        val diseaseName: String = "",
        @SerialName("ranking")
        val ranking: Int = 0,
        @SerialName("percent")
        val percent: Int = 0,
        @SerialName("rating")
        val rating: Int = 0,
        @SerialName("description")
        val description: String = "",
        @SerialName("type")
        val type: String = "",
        @SerialName("site")
        val site: String = "",
        @SerialName("reason")
        val reason: String = "",
        @SerialName("mild")
        val mild: String = "",
        @SerialName("severe")
        val severe: String = "",
        @SerialName("preventive")
        val preventive: String = "",
        @SerialName("caution")
        val caution: String = "",
        @SerialName("symptoms")
        val symptoms: List<SymptomItem> = emptyList(),
        @SerialName("drugs")
        val drugs: List<DrugItem> = emptyList(),
    )
}