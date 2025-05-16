package com.peekaboo.data.base

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BaseResponseNoData(
    @SerialName("success")
    val success: Boolean? = false,
    @SerializedName("data")
    val data: Boolean? = false,
    @SerialName("error")
    val error: ErrorResponse? = null,
)