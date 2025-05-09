package com.peekaboo.data.entity.request.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequestDto(
    @SerialName("location")
    val location: String,
    @SerialName("language")
    val language: String,
    @SerialName("birth")
    val birth: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("bloodType")
    val bloodType: String,
    @SerialName("skinType")
    val skinType: String,
    @SerialName("allergyList")
    val allergyList: List<AllergyItem>,
    @SerialName("onDiseaseList")
    val onDiseaseList: List<DiseaseItem>,
) {
    @Serializable
    data class AllergyItem(
        @SerialName("description")
        val description: String,
    )

    @Serializable
    data class DiseaseItem(
        @SerialName("description")
        val description: String,
    )
}