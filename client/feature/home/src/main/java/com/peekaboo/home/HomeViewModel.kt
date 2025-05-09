package com.peekaboo.home

import androidx.lifecycle.viewModelScope
import com.peekaboo.domain.entity.response.DiseaseBannerItem
import com.peekaboo.domain.usecase.crawl.GetTopCrawlingUseCase
import com.peekaboo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTopCrawlingUseCase: GetTopCrawlingUseCase,
) : BaseViewModel<HomePageState>(
    HomePageState()
) {

    init {
        initSetDiseaseBannerList()
        setDiseaseBannerList()
    }

    private fun initSetDiseaseBannerList() {
        val diseaseList: List<DiseaseBannerItem> = listOf(
            DiseaseBannerItem(
                "콜레라",
                "앙골라",
                "25년 1월 초부터 3월 23일까지 앙골라에서 콜레라 누적 8,543명 발생, 329명 사망 보고"
            ),
        )

        updateState(
            uiState.value.copy(
                diseaseBannerList = diseaseList
            )
        )
    }

    private fun setDiseaseBannerList() {
        viewModelScope.launch {
            getTopCrawlingUseCase(request = "Ko").collect {
                resultResponse(it, ::onSuccessGetDiseaseBanner)
            }
        }
    }

    private fun onSuccessGetDiseaseBanner(diseaseList: List<DiseaseBannerItem>) {
        updateState(
            uiState.value.copy(
                diseaseBannerList = diseaseList
            )
        )
    }
}