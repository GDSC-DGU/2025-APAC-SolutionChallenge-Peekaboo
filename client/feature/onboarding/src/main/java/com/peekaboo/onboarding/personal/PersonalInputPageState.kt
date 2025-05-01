package com.peekaboo.onboarding.personal

import com.peekaboo.ui.base.PageState

data class PersonalInputPageState(
    val selectedSex: String = "",
    val bloodType: String = "",
    val birthInput: String = ""
) : PageState