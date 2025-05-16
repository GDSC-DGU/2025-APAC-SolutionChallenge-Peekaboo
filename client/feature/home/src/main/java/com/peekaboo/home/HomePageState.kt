package com.peekaboo.home

import com.peekaboo.domain.entity.response.DiseaseBannerItem
import com.peekaboo.ui.base.PageState

data class HomePageState (
    val diseaseBannerList: List<DiseaseBannerItem> = emptyList()
): PageState