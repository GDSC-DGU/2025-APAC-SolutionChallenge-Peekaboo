package com.peekaboo.onboarding.personal

import com.peekaboo.domain.entity.request.CreateUserModel
import com.peekaboo.ui.base.PageState

data class PersonalInputPageState(
    val userModel: CreateUserModel = CreateUserModel(),
    val selectedSex: String = "",
    val bloodType: String = "",
    val birthInput: String = ""
) : PageState