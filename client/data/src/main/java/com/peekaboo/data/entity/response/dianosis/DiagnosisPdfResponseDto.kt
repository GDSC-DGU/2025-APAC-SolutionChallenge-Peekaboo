package com.peekaboo.data.entity.response.dianosis

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiagnosisPdfResponseDto (
    @SerialName("url")
    val url: String = ""
)