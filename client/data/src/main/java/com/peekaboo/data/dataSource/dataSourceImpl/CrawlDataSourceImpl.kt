package com.peekaboo.data.dataSource.dataSourceImpl

import com.peekaboo.data.base.BaseResponse
import com.peekaboo.data.dataSource.CrawlDataSource
import com.peekaboo.data.entity.response.crawl.CrawlResponseDto
import com.peekaboo.data.service.CrawlService
import retrofit2.Response
import javax.inject.Inject

class CrawlDataSourceImpl @Inject constructor(
    private val crawlService: CrawlService,
) : CrawlDataSource {

    override suspend fun getTopCrawling(lang: String): Response<BaseResponse<CrawlResponseDto>> =
        crawlService.topCrawling(lang)
}