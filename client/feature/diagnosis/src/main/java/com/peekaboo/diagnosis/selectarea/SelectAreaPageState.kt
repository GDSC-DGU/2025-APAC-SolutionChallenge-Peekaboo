package com.peekaboo.diagnosis.selectarea

import com.peekaboo.design_system.DiagnosisSelectAreaFront
import com.peekaboo.design_system.R
import com.peekaboo.domain.entity.request.DiagnosisModel
import com.peekaboo.ui.base.PageState

data class SelectAreaPageState (
    val diagnosisContent: DiagnosisModel = DiagnosisModel(),
    val selectedArea: String = "",
    val selectedShape: String = DiagnosisSelectAreaFront,
    val selectedShapeImg: Int = R.drawable.ic_body_front,
): PageState