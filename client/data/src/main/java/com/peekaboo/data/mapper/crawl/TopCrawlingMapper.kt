package com.peekaboo.data.mapper.crawl

import com.peekaboo.data.base.BaseMapper
import com.peekaboo.data.base.BaseResponse
import com.peekaboo.data.entity.response.crawl.CrawlResponseDto
import com.peekaboo.domain.entity.response.DiseaseBannerItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object TopCrawlingMapper : BaseMapper() {

    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<CrawlResponseDto>>): Flow<Result<List<DiseaseBannerItem>>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.let { data ->
                    data.weeklyInfo.map { info ->
                        DiseaseBannerItem(
                            name = info.name,
                            location = info.location,
                            description = info.description
                        )
                    }
                } ?: listOf(DiseaseBannerItem())
            }
        )
    }
}