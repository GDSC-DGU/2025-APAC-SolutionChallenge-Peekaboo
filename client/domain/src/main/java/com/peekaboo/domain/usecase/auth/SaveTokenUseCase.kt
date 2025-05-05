package com.peekaboo.domain.usecase.auth

import com.peekaboo.domain.base.UseCase
import com.peekaboo.domain.entity.response.TokenStoreModel
import com.peekaboo.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) : UseCase<TokenStoreModel, Result<Unit>>() {

    override suspend fun invoke(request: TokenStoreModel): Flow<Result<Unit>> {
        return authRepository.saveToken(request)
    }
}