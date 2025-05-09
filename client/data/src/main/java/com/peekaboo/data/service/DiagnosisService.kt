package com.peekaboo.data.service

import com.peekaboo.data.base.BaseResponse
import com.peekaboo.data.base.EndPoints
import com.peekaboo.data.entity.response.dianosis.DiagnosisConstResponseDto
import com.peekaboo.data.entity.response.dianosis.DiagnosisHistoryDetailResponseDto
import com.peekaboo.data.entity.response.dianosis.DiagnosisHistoryResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DiagnosisService {

    @GET(EndPoints.Diagnosis.HISTORY)
    suspend fun diagnosisHistory(): Response<BaseResponse<DiagnosisHistoryResponseDto>>

    @GET(EndPoints.Diagnosis.HISTORYDETAIL)
    suspend fun diagnosisHistoryDetail(
        @Path("historyId") historyId: Int
    ): Response<BaseResponse<DiagnosisHistoryDetailResponseDto>>

    @GET(EndPoints.Diagnosis.HISTORYCONST)
    suspend fun diagnosisConst(
        @Path("constId") constId: Int
    ): Response<BaseResponse<DiagnosisConstResponseDto>>
}