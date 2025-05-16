package com.peekaboo.domain.usecase.diagnosis

import com.peekaboo.domain.base.UseCase
import com.peekaboo.domain.entity.request.diagnosis.DiagnosisAIRequestModel
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryDetailModel
import com.peekaboo.domain.repository.DiagnosisRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDiagnosisAIUseCase @Inject constructor(
    private val diagnosisRepository: DiagnosisRepository,
): UseCase<DiagnosisAIRequestModel, Result<DiagnosisHistoryDetailModel>>() {

    override suspend fun invoke(request: DiagnosisAIRequestModel): Flow<Result<DiagnosisHistoryDetailModel>> =
        diagnosisRepository.getDiagnosisAI(request)
}