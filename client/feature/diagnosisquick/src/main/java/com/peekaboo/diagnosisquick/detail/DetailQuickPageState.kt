package com.peekaboo.diagnosisquick.detail

import com.peekaboo.domain.entity.response.diagnosis.DiagnosisConstModel
import com.peekaboo.ui.base.PageState

data class DetailQuickPageState (
    val diagnosisModel: DiagnosisConstModel = DiagnosisConstModel()
): PageState