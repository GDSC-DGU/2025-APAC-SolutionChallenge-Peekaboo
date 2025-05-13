package com.peekaboo.data.dataSource.dataSourceImpl

import com.peekaboo.data.base.BaseResponse
import com.peekaboo.data.dataSource.DiagnosisDataSource
import com.peekaboo.data.entity.response.dianosis.DiagnosisConstResponseDto
import com.peekaboo.data.entity.response.dianosis.DiagnosisHistoryDetailResponseDto
import com.peekaboo.data.entity.response.dianosis.DiagnosisHistoryResponseDto
import com.peekaboo.data.entity.response.dianosis.DiagnosisPdfResponseDto
import com.peekaboo.data.service.DiagnosisService
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class DiagnosisDataSourceImpl @Inject constructor(
    private val diagnosisService: DiagnosisService,
) : DiagnosisDataSource {

    override suspend fun getDiagnosisHistory(): Response<BaseResponse<DiagnosisHistoryResponseDto>> =
        diagnosisService.diagnosisHistory()

    override suspend fun getDiagnosisHistoryDetail(historyId: Int): Response<BaseResponse<DiagnosisHistoryDetailResponseDto>> =
        diagnosisService.diagnosisHistoryDetail(historyId)

    override suspend fun getDiagnosisConst(constId: Int): Response<BaseResponse<DiagnosisConstResponseDto>> =
        diagnosisService.diagnosisConst(constId)

    override suspend fun getDiagnosisPdf(
        diagnosisId: Int,
        lang: String,
    ): Response<BaseResponse<DiagnosisPdfResponseDto>> =
        diagnosisService.diagnosisPdf(diagnosisId, lang)


    override suspend fun getDiagnosisAI(
        area: String,
        image: MultipartBody.Part?,
        symptom: String,
    ): Response<BaseResponse<DiagnosisHistoryDetailResponseDto>> =
        diagnosisService.diagnosisAI(area, image, symptom)
}