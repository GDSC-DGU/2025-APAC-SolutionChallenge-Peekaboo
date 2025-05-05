package com.peekaboo.domain.repository

import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryResponseModel
import kotlinx.coroutines.flow.Flow

interface DiagnosisRepository {
    suspend fun getDiagnosisHistory(): Flow<Result<DiagnosisHistoryResponseModel>>
}