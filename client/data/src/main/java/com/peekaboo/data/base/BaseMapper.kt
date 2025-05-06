package com.peekaboo.data.base

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class BaseMapper {

    fun <DTO, MODEL> baseMapper(
        apiCall: suspend () -> Response<BaseResponse<DTO>>?,
        responseToModel: (DTO?) -> MODEL,
    ): Flow<Result<MODEL>> = flow {
        val response = apiCall()
        val defaultModel = responseToModel(null)

        response?.let {
            val apiResponse = response.body()

            if (response.isSuccessful && apiResponse != null) {
                if (apiResponse.error != null) {
                    emit(Result.failure(Exception(apiResponse.error.message)))
                } else {
                    val data = responseToModel(apiResponse.data) ?: defaultModel
                    emit(Result.success(data))
                }
            } else {
                val errorBody = response.errorBody()?.string() ?: ""
                val errorMessage = fromGson<DTO>(errorBody).error?.message
                emit(Result.failure(Exception(errorMessage)))
            }
        } ?: emit(Result.success(defaultModel))

    }

    private fun <T> fromGson(json: String?): BaseResponse<T> {
        return Gson().fromJson(json, object : TypeToken<BaseResponse<T>>() {}.type)
            ?: BaseResponse()
    }
}