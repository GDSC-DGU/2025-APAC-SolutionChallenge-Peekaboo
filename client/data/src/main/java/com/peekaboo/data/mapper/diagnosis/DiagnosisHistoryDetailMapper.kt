package com.peekaboo.data.mapper.diagnosis

import com.peekaboo.data.base.BaseMapper
import com.peekaboo.data.base.BaseResponse
import com.peekaboo.data.entity.response.dianosis.DiagnosisHistoryDetailResponseDto
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryDetailModel
import com.peekaboo.domain.entity.response.diagnosis.DrugItem
import com.peekaboo.domain.entity.response.diagnosis.SymptomItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object DiagnosisHistoryDetailMapper : BaseMapper() {

    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<DiagnosisHistoryDetailResponseDto>>): Flow<Result<DiagnosisHistoryDetailModel>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.let { data ->
                    DiagnosisHistoryDetailModel(
                        customDescription = data.customDescription,
                        diagnosisId = data.diagnosisId,
                        diseaseList = data.diseaseList.map { disease ->
                            DiagnosisHistoryDetailModel.DiseaseDetailItem(
                                diseaseId = disease.diseaseId,
                                diseaseName = disease.diseaseName,
                                ranking = disease.ranking,
                                percent = disease.percent,
                                rating = disease.rating,
                                description = disease.description,
                                type = disease.type,
                                site = disease.site,
                                reason = disease.reason,
                                mild = disease.mild,
                                severe = disease.severe,
                                preventive = disease.preventive,
                                caution = disease.caution,
                                symptoms = disease.symptoms.map { symptom ->
                                    SymptomItem(
                                        name = symptom.name
                                    )
                                },
                                drugs = disease.drugs.map { drug ->
                                    DrugItem(
                                        name = drug.name,
                                        efficacy = drug.efficacy
                                    )
                                }
                            )
                        }
                    )
                } ?: DiagnosisHistoryDetailModel()
            }
        )
    }
}