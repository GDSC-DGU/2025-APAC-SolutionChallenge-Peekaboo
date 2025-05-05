package com.peekaboo.domain.usecase.auth

import com.peekaboo.domain.base.UseCase
import com.peekaboo.domain.entity.response.auth.LogInResponseModel
import com.peekaboo.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostLogInUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) : UseCase<Unit, Result<LogInResponseModel>>() {

    override suspend fun invoke(request: Unit): Flow<Result<LogInResponseModel>> =
        authRepository.postLogIn()
}