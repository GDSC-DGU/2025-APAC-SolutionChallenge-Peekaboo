package com.peekaboo.data.dataSource.dataSourceImpl

import com.peekaboo.data.base.BaseResponse
import com.peekaboo.data.dataSource.DiagnosisDataSource
import com.peekaboo.data.entity.response.dianosis.DiagnosisHistoryDetailResponseDto
import com.peekaboo.data.entity.response.dianosis.DiagnosisHistoryResponseDto
import com.peekaboo.data.service.DiagnosisService
import retrofit2.Response
import javax.inject.Inject

class DiagnosisDataSourceImpl @Inject constructor(
    private val diagnosisService: DiagnosisService,
) : DiagnosisDataSource {

    override suspend fun getDiagnosisHistory(): Response<BaseResponse<DiagnosisHistoryResponseDto>> =
        diagnosisService.diagnosisHistory()

    override suspend fun getDiagnosisHistoryDetail(historyId: Int): Response<BaseResponse<DiagnosisHistoryDetailResponseDto>> =
        diagnosisService.diagnosisHistoryDetail(historyId)
}