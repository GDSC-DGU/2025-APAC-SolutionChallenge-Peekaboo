package com.peekaboo.data.service

import com.peekaboo.data.base.BaseResponse
import com.peekaboo.data.base.EndPoints
import com.peekaboo.data.entity.response.dianosis.DiagnosisConstResponseDto
import com.peekaboo.data.entity.response.dianosis.DiagnosisHistoryDetailResponseDto
import com.peekaboo.data.entity.response.dianosis.DiagnosisHistoryResponseDto
import com.peekaboo.data.entity.response.dianosis.DiagnosisPdfResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DiagnosisService {

    @GET(EndPoints.Diagnosis.HISTORY)
    suspend fun diagnosisHistory(): Response<BaseResponse<DiagnosisHistoryResponseDto>>

    @GET(EndPoints.Diagnosis.HISTORYDETAIL)
    suspend fun diagnosisHistoryDetail(
        @Path("historyId") historyId: Int,
    ): Response<BaseResponse<DiagnosisHistoryDetailResponseDto>>

    @GET(EndPoints.Diagnosis.HISTORYCONST)
    suspend fun diagnosisConst(
        @Path("constId") constId: Int,
    ): Response<BaseResponse<DiagnosisConstResponseDto>>

    @GET(EndPoints.Diagnosis.DIAGNOSISPDF)
    suspend fun diagnosisPdf(
        @Path("diagnosisId") diagnosisId: Int,
        @Query("lang") lang: String
    ): Response<BaseResponse<DiagnosisPdfResponseDto>>
}