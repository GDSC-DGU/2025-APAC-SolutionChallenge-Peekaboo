package com.peekaboo.onboarding.skin

import com.peekaboo.domain.entity.request.CreateUserModel
import com.peekaboo.ui.base.PageState

data class SkinColorPageState (
    val userModel: CreateUserModel = CreateUserModel(),
    val selectedColor: String = ""
): PageState