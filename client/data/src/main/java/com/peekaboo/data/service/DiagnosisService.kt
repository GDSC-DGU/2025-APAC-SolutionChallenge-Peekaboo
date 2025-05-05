package com.peekaboo.data.service

import com.peekaboo.data.base.BaseResponse
import com.peekaboo.data.base.EndPoints
import com.peekaboo.data.entity.response.dianosis.DiagnosisHistoryResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface DiagnosisService {

    @GET(EndPoints.Diagnosis.HISTORY)
    suspend fun diagnosisHistory(): Response<BaseResponse<DiagnosisHistoryResponseDto>>
}