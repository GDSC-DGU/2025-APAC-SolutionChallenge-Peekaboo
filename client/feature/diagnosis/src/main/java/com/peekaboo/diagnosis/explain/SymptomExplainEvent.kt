package com.peekaboo.diagnosis.explain

import com.peekaboo.ui.base.Event

sealed class SymptomExplainEvent : Event {
    data object GoToDiagnosisResultPage : SymptomExplainEvent()
}