package com.peekaboo.data.mapper.auth

import com.peekaboo.data.base.BaseMapper
import com.peekaboo.data.base.BaseResponse
import com.peekaboo.data.entity.response.auth.LogInResponseDto
import com.peekaboo.domain.entity.response.TokenStoreModel
import com.peekaboo.domain.entity.response.auth.LogInResponseModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object LogInMapper : BaseMapper() {

    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<LogInResponseDto>>): Flow<Result<LogInResponseModel>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.let { data ->
                    LogInResponseModel(
                        jwtTokenDto = TokenStoreModel(
                            accessToken = data.jwtTokenDto.accessToken,
                            refreshToken = data.jwtTokenDto.refreshToken
                        ),
                        userFlag = data.userFlag
                    )
                } ?: LogInResponseModel()
            }
        )
    }
}