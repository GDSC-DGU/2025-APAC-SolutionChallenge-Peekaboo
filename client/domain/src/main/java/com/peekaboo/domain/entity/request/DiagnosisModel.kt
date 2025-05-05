package com.peekaboo.domain.entity.request

import java.io.File

data class DiagnosisModel (
    val selectedArea: String = "",
    val photo: File? = null,
    val symptomsExplain: String = ""
)