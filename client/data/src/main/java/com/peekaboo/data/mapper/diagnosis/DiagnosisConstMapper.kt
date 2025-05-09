package com.peekaboo.data.mapper.diagnosis

import com.peekaboo.data.base.BaseMapper
import com.peekaboo.data.base.BaseResponse
import com.peekaboo.data.entity.response.dianosis.DiagnosisConstResponseDto
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisConstModel
import com.peekaboo.domain.entity.response.diagnosis.DrugItem
import com.peekaboo.domain.entity.response.diagnosis.SymptomItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object DiagnosisConstMapper : BaseMapper() {

    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<DiagnosisConstResponseDto>>): Flow<Result<DiagnosisConstModel>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.let { data ->
                    DiagnosisConstModel(
                        diseaseName = data.diseaseName,
                        ranting = data.ranting,
                        description = data.description,
                        type = data.type,
                        site = data.site,
                        reason = data.reason,
                        mild = data.mild,
                        severe = data.severe,
                        preventive = data.preventive,
                        caution = data.caution,
                        symptoms = data.symptoms.map { symptom ->
                            SymptomItem(
                                name = symptom.name
                            )
                        },
                        drugs = data.drugs.map { drug ->
                            DrugItem(
                                name = drug.name,
                                efficacy = drug.efficacy
                            )
                        }
                    )
                } ?: DiagnosisConstModel()
            }
        )
    }
}