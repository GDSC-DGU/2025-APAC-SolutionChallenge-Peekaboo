package com.peekaboo.diagnosisquick.detail

import com.peekaboo.design_system.R
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisConstModel
import com.peekaboo.ui.base.PageState

data class DetailQuickPageState (
    val diagnosisModel: DiagnosisConstModel = DiagnosisConstModel(),
    val diagnosisImg: Int = R.drawable.ic_disease_1
): PageState