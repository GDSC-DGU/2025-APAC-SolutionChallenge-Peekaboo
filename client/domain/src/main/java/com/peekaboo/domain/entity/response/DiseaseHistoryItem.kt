package com.peekaboo.domain.entity.response

data class DiseaseHistoryItem (
    val historyId: Int = 0,
    val date: String = "",
    val diseaseName: String = "",
    val symptoms: String = "",
    val methods: String = "",
    val rankingSecondName: String = "",
    val rankingThirdName: String = ""
)