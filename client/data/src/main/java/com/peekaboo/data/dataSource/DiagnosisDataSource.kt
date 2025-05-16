package com.peekaboo.data.dataSource

import com.peekaboo.data.base.BaseResponse
import com.peekaboo.data.entity.response.dianosis.DiagnosisConstResponseDto
import com.peekaboo.data.entity.response.dianosis.DiagnosisHistoryDetailResponseDto
import com.peekaboo.data.entity.response.dianosis.DiagnosisHistoryResponseDto
import com.peekaboo.data.entity.response.dianosis.DiagnosisPdfResponseDto
import okhttp3.MultipartBody
import retrofit2.Response

interface DiagnosisDataSource {
    suspend fun getDiagnosisHistory(): Response<BaseResponse<DiagnosisHistoryResponseDto>>
    suspend fun getDiagnosisHistoryDetail(historyId: Int): Response<BaseResponse<DiagnosisHistoryDetailResponseDto>>
    suspend fun getDiagnosisConst(constId: Int): Response<BaseResponse<DiagnosisConstResponseDto>>
    suspend fun getDiagnosisPdf(
        diagnosisId: Int,
        lang: String,
    ): Response<BaseResponse<DiagnosisPdfResponseDto>>

    suspend fun getDiagnosisAI(
        area: String,
        image: MultipartBody.Part? = null,
        symptom: String,
    ): Response<BaseResponse<DiagnosisHistoryDetailResponseDto>>
}