package com.peekaboo.domain.entity.response.diagnosis

data class DiagnosisConstModel (
    val diseaseName: String = "",
    val ranting: Int = 0,
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