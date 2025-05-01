package com.peekaboo.domain.entity.request

data class CreateUserModel(
    val location: String = "",
    val language: String = "",
    val birth: String = "",
    val sex: String = "",
    val bloodType: String = "",
    val skinType: String = "",
    val allergyList: List<InputDescriptionModel> = emptyList(),
    val diseaseList: List<InputDescriptionModel> = emptyList(),
)