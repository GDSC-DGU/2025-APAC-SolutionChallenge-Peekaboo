package com.peekaboo.domain.entity.response.auth

import com.peekaboo.domain.entity.response.TokenStoreModel

data class LogInResponseModel(
    val jwtTokenDto: TokenStoreModel = TokenStoreModel(),
    val userFlag: Boolean = false,
)