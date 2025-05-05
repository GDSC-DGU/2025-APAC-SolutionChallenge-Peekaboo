package com.peekaboo.domain.entity.response

data class TokenStoreModel (
    val accessToken: String = "",
    val refreshToken: String = "",
)