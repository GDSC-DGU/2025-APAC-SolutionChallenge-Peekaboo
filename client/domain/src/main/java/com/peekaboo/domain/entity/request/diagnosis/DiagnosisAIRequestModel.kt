package com.peekaboo.domain.entity.request.diagnosis

import com.peekaboo.domain.entity.request.ImageModel

data class DiagnosisAIRequestModel (
    val area: String,
    val image: ImageModel,
    val symptoms: String
)