package com.peekaboo.onboarding.allergy

import com.peekaboo.ui.base.PageState

data class AllergyExistPageState(
    val allergyExistInputList: List<String> = listOf(""),
) : PageState