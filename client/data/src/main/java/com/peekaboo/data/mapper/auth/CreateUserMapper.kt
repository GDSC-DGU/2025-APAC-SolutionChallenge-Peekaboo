package com.peekaboo.data.mapper.auth

import com.peekaboo.data.base.BaseMapper
import com.peekaboo.data.base.BaseResponse
import com.peekaboo.data.entity.request.auth.CreateUserRequestDto
import com.peekaboo.domain.entity.request.CreateUserModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object CreateUserMapper : BaseMapper() {

    fun CreateUserModel.toDto() = CreateUserRequestDto(
        location = location,
        language = language,
        birth = birth,
        gender = gender,
        bloodType = bloodType,
        skinType = skinType,
        allergyList = allergyList.map { allergy ->
            CreateUserRequestDto.AllergyItem(description = allergy.description)
        },
        onDiseaseList = diseaseList.map { disease ->
            CreateUserRequestDto.DiseaseItem(description = disease.description)
        }
    )

    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<Unit>>): Flow<Result<Unit>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = {}
        )
    }
}