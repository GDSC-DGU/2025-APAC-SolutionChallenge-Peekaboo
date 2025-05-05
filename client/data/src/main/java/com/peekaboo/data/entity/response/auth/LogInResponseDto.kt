package com.peekaboo.data.entity.response.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LogInResponseDto (
    @SerialName("jwtTokenDto")
    val jwtTokenDto: TokenDto = TokenDto(),
    @SerialName("userFlag")
    val userFlag: Boolean = false
) {
    @Serializable
    data class TokenDto(
        @SerialName("accessToken")
        val accessToken: String = "",
        @SerialName("refreshToken")
        val refreshToken: String = ""
    )
}