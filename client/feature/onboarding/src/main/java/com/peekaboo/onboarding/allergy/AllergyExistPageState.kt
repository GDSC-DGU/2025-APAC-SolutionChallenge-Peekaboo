package com.peekaboo.onboarding.allergy

import com.peekaboo.domain.entity.request.CreateUserModel
import com.peekaboo.domain.entity.request.InputDescriptionModel
import com.peekaboo.ui.base.PageState

data class AllergyExistPageState(
    val userModel: CreateUserModel = CreateUserModel(),
    val allergyExistInputList: List<InputDescriptionModel> = listOf(InputDescriptionModel()),
) : PageState