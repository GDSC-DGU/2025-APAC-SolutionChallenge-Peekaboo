package com.peekaboo.data.mapper.diagnosis

import com.peekaboo.data.base.BaseMapper
import com.peekaboo.data.base.BaseResponse
import com.peekaboo.data.entity.response.dianosis.DiagnosisHistoryResponseDto
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryResponseModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object DiagnosisHistoryMapper : BaseMapper() {

    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<DiagnosisHistoryResponseDto>>): Flow<Result<DiagnosisHistoryResponseModel>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.let { data ->
                    DiagnosisHistoryResponseModel(
                        historyList = data.historyList.map { history ->
                            DiagnosisHistoryResponseModel.DiagnosisHistoryItem(
                                diagnosisId = history.diagnosisId,
                                customDescription = history.customDescription,
                                createAt = history.createAt,
                                diseaseList = history.diseaseList.map { disease ->
                                    DiagnosisHistoryResponseModel.DiagnosisHistoryItem.DiseaseItem(
                                        diseaseId = disease.diseaseId,
                                        diseaseName = disease.diseaseName,
                                        ranking = disease.ranking
                                    )
                                }
                            )
                        }
                    )
                } ?: DiagnosisHistoryResponseModel()
            }
        )
    }
}