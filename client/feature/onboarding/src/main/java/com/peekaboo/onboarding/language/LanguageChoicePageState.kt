package com.peekaboo.onboarding.language

import com.peekaboo.ui.base.PageState

data class LanguageChoicePageState (
    val selectedLocation: String = "",
    val selectedLanguage: String = ""
): PageState