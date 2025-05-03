package com.peekaboo.home

import com.peekaboo.domain.entity.response.DiseaseBannerItem
import com.peekaboo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : BaseViewModel<HomePageState>(
    HomePageState()
) {

    init {
        initSetDiseaseBannerList()
    }

    private fun initSetDiseaseBannerList() {
        val diseaseList: List<DiseaseBannerItem> = listOf(
            DiseaseBannerItem(
                "콜레라",
                "앙골라",
                "25년 1월 초부터 3월 23일까지 앙골라에서 콜레라 누적 8,543명 발생, 329명 사망 보고"
            ),
            DiseaseBannerItem(
                "말라리아",
                "앙골라",
                "25년 1월 초부터 3월 23일까지 앙골라에서 콜레라 누적 8,543명 발생, 329명 사망 보고"
            ),
            DiseaseBannerItem("홍역", "앙골라", "25년 1월 초부터 3월 23일까지 앙골라에서 콜레라 누적 8,543명 발생, 329명 사망 보고")
        )

        updateState(
            uiState.value.copy(
                diseaseBannerList = diseaseList
            )
        )
    }
}