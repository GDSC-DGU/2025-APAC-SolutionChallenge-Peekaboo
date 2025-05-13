package com.peekaboo.home

import androidx.lifecycle.viewModelScope
import com.peekaboo.domain.entity.response.DiseaseBannerItem
import com.peekaboo.domain.usecase.crawl.GetTopCrawlingUseCase
import com.peekaboo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
                "whooping cough",
                "several countries",
                "In the Americas, the number of cases of whooping cough has been increasing rapidly in the US and Mexico for 25 years, and Japan has also recently seen an increase in cases, including deaths due to whooping cough related to antibiotic resistance, and Australia is also seeing a very high incidence compared to the average year."
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
            getTopCrawlingUseCase(request = "en").collect {
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