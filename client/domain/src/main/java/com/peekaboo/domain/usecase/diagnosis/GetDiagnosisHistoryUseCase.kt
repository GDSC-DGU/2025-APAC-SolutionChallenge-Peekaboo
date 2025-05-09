package com.peekaboo.domain.usecase.diagnosis

import com.peekaboo.domain.base.UseCase
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryResponseModel
import com.peekaboo.domain.repository.DiagnosisRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDiagnosisHistoryUseCase @Inject constructor(
    private val diagnosisRepository: DiagnosisRepository
): UseCase<Unit, Result<DiagnosisHistoryResponseModel>>() {

    override suspend fun invoke(request: Unit): Flow<Result<DiagnosisHistoryResponseModel>> =
        diagnosisRepository.getDiagnosisHistory()
}