package com.peekaboo.domain.usecase.diagnosis

import com.peekaboo.domain.base.UseCase
import com.peekaboo.domain.entity.request.diagnosis.DiagnosisPdfRequestModel
import com.peekaboo.domain.repository.DiagnosisRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDiagnosisPdfUseCase @Inject constructor(
    private val diagnosisRepository: DiagnosisRepository,
) : UseCase<DiagnosisPdfRequestModel, Result<String>>() {

    override suspend fun invoke(request: DiagnosisPdfRequestModel): Flow<Result<String>> =
        diagnosisRepository.getDiagnosisPdf(request)
}