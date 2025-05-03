package com.peekaboo.onboarding.diseasehistory

import com.peekaboo.domain.entity.request.CreateUserModel
import com.peekaboo.domain.entity.request.InputDescriptionModel
import com.peekaboo.ui.base.PageState

data class WriteDiseaseHistoryPageState(
    val userModel: CreateUserModel = CreateUserModel(),
    val diseaseHistoryInputList: List<InputDescriptionModel> = listOf(InputDescriptionModel()),
) : PageState