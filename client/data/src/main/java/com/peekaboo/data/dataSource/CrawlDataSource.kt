package com.peekaboo.data.dataSource

import com.peekaboo.data.base.BaseResponse
import com.peekaboo.data.entity.response.crawl.CrawlResponseDto
import retrofit2.Response

interface CrawlDataSource {
    suspend fun getTopCrawling(lang: String): Response<BaseResponse<CrawlResponseDto>>
}