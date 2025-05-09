package com.peekaboo.data.service

import com.peekaboo.data.base.BaseResponse
import com.peekaboo.data.base.EndPoints
import com.peekaboo.data.entity.response.crawl.CrawlResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CrawlService {

    @GET(EndPoints.Crawling.TOPBANNER)
    suspend fun topCrawling(
        @Query("target_lang") targetLang: String,
    ): Response<BaseResponse<CrawlResponseDto>>
}