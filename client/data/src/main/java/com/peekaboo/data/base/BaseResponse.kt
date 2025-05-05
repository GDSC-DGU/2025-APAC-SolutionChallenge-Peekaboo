package com.peekaboo.data.base

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("success")
    val success: Boolean? = false,
    @SerializedName("data")
    val data: T? = null,
    @SerialName("error")
    val error: ErrorResponse? = null,
)