package com.peekaboo.domain.usecase.diagnosis

import com.peekaboo.domain.base.UseCase
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisConstModel
import com.peekaboo.domain.repository.DiagnosisRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDiagnosisConstUseCase @Inject constructor(
    private val diagnosisRepository: DiagnosisRepository,
) : UseCase<Int, Result<DiagnosisConstModel>>() {

    override suspend fun invoke(request: Int): Flow<Result<DiagnosisConstModel>> =
        diagnosisRepository.getDiagnosisConst(request)
}