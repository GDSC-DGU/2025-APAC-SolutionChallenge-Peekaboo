package com.peekaboo.data.dataSource

import com.peekaboo.data.base.BaseResponse
import com.peekaboo.data.base.BaseResponseNoData
import com.peekaboo.data.entity.request.auth.CreateUserRequestDto
import com.peekaboo.data.entity.response.auth.LogInResponseDto
import retrofit2.Response

interface AuthDataSource {
    suspend fun postLogIn(): Response<BaseResponse<LogInResponseDto>>
    suspend fun postUser(body: CreateUserRequestDto): Response<BaseResponseNoData>
}