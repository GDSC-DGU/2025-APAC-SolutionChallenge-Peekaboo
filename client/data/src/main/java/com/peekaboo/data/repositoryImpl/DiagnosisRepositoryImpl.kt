package com.peekaboo.data.repositoryImpl

import com.peekaboo.data.dataSource.DiagnosisDataSource
import com.peekaboo.data.mapper.diagnosis.DiagnosisConstMapper
import com.peekaboo.data.mapper.diagnosis.DiagnosisHistoryDetailMapper
import com.peekaboo.data.mapper.diagnosis.DiagnosisHistoryMapper
import com.peekaboo.data.mapper.diagnosis.DiagnosisPdfMapper
import com.peekaboo.domain.entity.request.diagnosis.DiagnosisAIRequestModel
import com.peekaboo.domain.entity.request.diagnosis.DiagnosisPdfRequestModel
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisConstModel
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryDetailModel
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryResponseModel
import com.peekaboo.domain.repository.DiagnosisRepository
import com.peekaboo.multipart.FormDataUtil
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

    override suspend fun getDiagnosisConst(constId: Int): Flow<Result<DiagnosisConstModel>> =
        DiagnosisConstMapper.responseToModel(apiCall = {
            diagnosisDataSource.getDiagnosisConst(
                constId
            )
        })

    override suspend fun getDiagnosisPdf(diagnosis: DiagnosisPdfRequestModel): Flow<Result<String>> =
        DiagnosisPdfMapper.responseToModel(apiCall = {
            diagnosisDataSource.getDiagnosisPdf(
                diagnosis.diagnosisId,
                diagnosis.lang
            )
        })

    override suspend fun getDiagnosisAI(request: DiagnosisAIRequestModel): Flow<Result<DiagnosisHistoryDetailModel>> =
        DiagnosisHistoryDetailMapper.responseToModel(apiCall = {
            diagnosisDataSource.getDiagnosisAI(
                area = request.area,
                symptom = request.symptoms,
                image = FormDataUtil.getImageMultipartWithNull(
                    request.image.imageFile,
                    request.image.imgMultiPartName
                )
            )
        })

}