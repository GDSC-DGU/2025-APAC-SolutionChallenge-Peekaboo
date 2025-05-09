package com.peekaboo.domain.entity.response.diagnosis

data class DiagnosisHistoryDetailModel(
    val customDescription: String = "",
    val diseaseList: List<DiseaseDetailItem> = emptyList(),
) {
    data class DiseaseDetailItem(
        val diseaseId: Int = 0,
        val diseaseName: String = "",
        val ranking: Int = 0,
        val percent: Int = 0,
        val rating: Int = 0,
        val description: String = "",
        val type: String = "",
        val site: String = "",
        val reason: String = "",
        val mild: String = "",
        val severe: String = "",
        val preventive: String = "",
        val caution: String = "",
        val symptoms: List<SymptomItem> = emptyList(),
        val drugs: List<DrugItem> = emptyList(),
    )
}