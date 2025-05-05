package com.peekaboo.data.repositoryImpl

import com.peekaboo.data.dataSource.AuthDataSource
import com.peekaboo.data.dataStore.LocalDataStore
import com.peekaboo.data.mapper.auth.LogInMapper
import com.peekaboo.domain.entity.response.TokenStoreModel
import com.peekaboo.domain.entity.response.auth.LogInResponseModel
import com.peekaboo.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val localDataStore: LocalDataStore,
    private val authDataSource: AuthDataSource,
) : AuthRepository {

    override suspend fun saveToken(request: TokenStoreModel): Flow<Result<Unit>> = flow {
        localDataStore.saveAccessToken(request.accessToken)
        localDataStore.saveRefreshToken(request.refreshToken)
    }

    override suspend fun postLogIn(): Flow<Result<LogInResponseModel>> =
        LogInMapper.responseToModel(apiCall = { authDataSource.postLogIn() })
}