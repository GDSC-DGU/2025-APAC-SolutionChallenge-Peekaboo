package com.peekaboo.domain.repository

import com.peekaboo.domain.entity.response.TokenStoreModel
import com.peekaboo.domain.entity.response.auth.LogInResponseModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun saveToken(request: TokenStoreModel): Flow<Result<Unit>>
    suspend fun postLogIn(): Flow<Result<LogInResponseModel>>
}