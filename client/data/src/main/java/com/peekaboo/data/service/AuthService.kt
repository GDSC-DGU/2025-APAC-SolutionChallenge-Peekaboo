package com.peekaboo.data.service

import com.peekaboo.data.base.BaseResponse
import com.peekaboo.data.base.BaseResponseNoData
import com.peekaboo.data.base.EndPoints
import com.peekaboo.data.entity.request.auth.CreateUserRequestDto
import com.peekaboo.data.entity.response.auth.LogInResponseDto
import retrofit2.Response
import retrofit2.http.Body

import retrofit2.http.POST

interface AuthService {

    @POST(EndPoints.Auth.LOGIN)
    suspend fun login(): Response<BaseResponse<LogInResponseDto>>

    @POST(EndPoints.Auth.USER)
    suspend fun createUser(
        @Body body: CreateUserRequestDto,
    ): Response<BaseResponseNoData>
}