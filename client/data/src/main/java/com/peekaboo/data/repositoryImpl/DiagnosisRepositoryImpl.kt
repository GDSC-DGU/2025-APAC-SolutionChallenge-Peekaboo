package com.peekaboo.data.repositoryImpl

import com.peekaboo.data.dataSource.DiagnosisDataSource
import com.peekaboo.data.mapper.diagnosis.DiagnosisHistoryDetailMapper
import com.peekaboo.data.mapper.diagnosis.DiagnosisHistoryMapper
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryDetailModel
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryResponseModel
import com.peekaboo.domain.repository.DiagnosisRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DiagnosisRepositoryImpl @Inject constructor(
    private val diagnosisDataSource: DiagnosisDataSource,
) : DiagnosisRepository {

    override suspend fun getDiagnosisHistory(): Flow<Result<DiagnosisHistoryResponseModel>> =
        DiagnosisHistoryMapper.responseToModel(apiCall = { diagnosisDataSource.getDiagnosisHistory() })

    override suspend fun getDiagnosisHistoryDetail(historyId: Int): Flow<Result<DiagnosisHistoryDetailModel>> =
        DiagnosisHistoryDetailMapper.responseToModel(apiCall = {
            diagnosisDataSource.getDiagnosisHistoryDetail(
                historyId
            )
        })
}