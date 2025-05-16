package com.peekaboo.onboarding.diseasehistory

import com.peekaboo.ui.base.Event

sealed class WriteDiseaseHistoryEvent : Event {
    data object GoToMainPage : WriteDiseaseHistoryEvent()
}