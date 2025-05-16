package com.peekaboo.data.mapper.diagnosis

import com.peekaboo.data.base.BaseMapper
import com.peekaboo.data.base.BaseResponse
import com.peekaboo.data.entity.response.dianosis.DiagnosisPdfResponseDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object DiagnosisPdfMapper : BaseMapper() {

    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<DiagnosisPdfResponseDto>>): Flow<Result<String>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.let { data ->
                    data.url
                } ?: ""
            }
        )
    }
}