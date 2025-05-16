package com.peekaboo.data.entity.response.dianosis

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SymptomItem(
    @SerialName("name")
    val name: String = "",
)