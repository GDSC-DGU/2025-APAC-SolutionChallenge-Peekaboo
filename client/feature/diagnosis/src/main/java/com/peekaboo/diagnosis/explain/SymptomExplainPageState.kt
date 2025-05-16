package com.peekaboo.diagnosis.explain

import com.peekaboo.domain.entity.request.DiagnosisModel
import com.peekaboo.ui.base.PageState

data class SymptomExplainPageState (
    val diagnosisContent: DiagnosisModel = DiagnosisModel(),
    val explainInput: String = ""
): PageState