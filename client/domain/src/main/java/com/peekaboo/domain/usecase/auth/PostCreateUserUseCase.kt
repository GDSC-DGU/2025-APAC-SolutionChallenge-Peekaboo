package com.peekaboo.domain.usecase.auth

import com.peekaboo.domain.base.UseCase
import com.peekaboo.domain.entity.request.CreateUserModel
import com.peekaboo.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostCreateUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) : UseCase<CreateUserModel, Result<Boolean>>() {

    override suspend fun invoke(request: CreateUserModel): Flow<Result<Boolean>> =
        authRepository.postCreateUser(request)
}