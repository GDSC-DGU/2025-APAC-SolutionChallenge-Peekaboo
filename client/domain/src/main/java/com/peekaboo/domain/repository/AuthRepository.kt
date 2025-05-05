package com.peekaboo.domain.repository

import com.peekaboo.domain.entity.response.TokenStoreModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun saveToken(request: TokenStoreModel): Flow<Result<Unit>>
}