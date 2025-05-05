package com.peekaboo.data.dataSource.dataSourceImpl

import com.peekaboo.data.base.BaseResponse
import com.peekaboo.data.dataSource.AuthDataSource
import com.peekaboo.data.entity.request.auth.CreateUserRequestDto
import com.peekaboo.data.entity.response.auth.LogInResponseDto
import com.peekaboo.data.service.AuthService
import retrofit2.Response
import javax.inject.Inject

data class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService,
) : AuthDataSource {

    override suspend fun postLogIn(): Response<BaseResponse<LogInResponseDto>> =
        authService.login()

    override suspend fun postUser(body: CreateUserRequestDto): Response<BaseResponse<Unit>> =
        authService.createUser(body)
}