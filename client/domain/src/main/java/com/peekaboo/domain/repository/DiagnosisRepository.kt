package com.peekaboo.domain.repository

import com.peekaboo.domain.entity.request.diagnosis.DiagnosisAIRequestModel
import com.peekaboo.domain.entity.request.diagnosis.DiagnosisPdfRequestModel
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisConstModel
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryDetailModel
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryResponseModel
import kotlinx.coroutines.flow.Flow

interface DiagnosisRepository {
    suspend fun getDiagnosisHistory(): Flow<Result<DiagnosisHistoryResponseModel>>
    suspend fun getDiagnosisHistoryDetail(historyId: Int): Flow<Result<DiagnosisHistoryDetailModel>>
    suspend fun getDiagnosisConst(constId: Int): Flow<Result<DiagnosisConstModel>>
    suspend fun getDiagnosisPdf(diagnosis: DiagnosisPdfRequestModel): Flow<Result<String>>
    suspend fun getDiagnosisAI(request: DiagnosisAIRequestModel): Flow<Result<DiagnosisHistoryDetailModel>>
}