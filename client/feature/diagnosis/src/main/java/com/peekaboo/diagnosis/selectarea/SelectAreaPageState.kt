package com.peekaboo.diagnosis.selectarea

import com.peekaboo.domain.entity.request.DiagnosisModel
import com.peekaboo.ui.base.PageState

data class SelectAreaPageState (
    val diagnosisContent: DiagnosisModel = DiagnosisModel(),
    val selectedArea: String = ""
): PageState