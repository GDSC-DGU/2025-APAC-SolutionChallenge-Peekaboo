package com.peekaboo.domain.repository

import com.peekaboo.domain.entity.response.DiseaseBannerItem
import kotlinx.coroutines.flow.Flow

interface CrawlRepository {
    suspend fun getTopCrawling(lang: String): Flow<Result<List<DiseaseBannerItem>>>
}