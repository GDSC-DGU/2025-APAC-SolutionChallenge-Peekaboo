package com.peekaboo.data.entity.response.dianosis

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DrugItem(
    @SerialName("name")
    val name: String = "",
    @SerialName("efficacy")
    val efficacy: String = "",
)