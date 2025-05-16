package com.peekaboo.diagnosis.picture

import com.peekaboo.domain.entity.request.DiagnosisModel
import com.peekaboo.ui.base.PageState
import java.io.File

data class UploadPicturePageState (
    val diagnosisContent: DiagnosisModel = DiagnosisModel(),
    val selectedImg: File? = null
): PageState