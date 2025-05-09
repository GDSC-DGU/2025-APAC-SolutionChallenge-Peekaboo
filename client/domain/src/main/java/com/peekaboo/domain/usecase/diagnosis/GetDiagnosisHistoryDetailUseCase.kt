package com.peekaboo.domain.usecase.diagnosis

import com.peekaboo.domain.base.UseCase
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryDetailModel
import com.peekaboo.domain.repository.DiagnosisRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDiagnosisHistoryDetailUseCase @Inject constructor(
    private val diagnosisRepository: DiagnosisRepository,
) : UseCase<Int, Result<DiagnosisHistoryDetailModel>>() {

    override suspend fun invoke(request: Int): Flow<Result<DiagnosisHistoryDetailModel>> =
        diagnosisRepository.getDiagnosisHistoryDetail(request)
}