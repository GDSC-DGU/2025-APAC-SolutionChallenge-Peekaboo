package com.peekaboo.data.repositoryImpl

import com.peekaboo.data.dataSource.CrawlDataSource
import com.peekaboo.data.mapper.crawl.TopCrawlingMapper
import com.peekaboo.domain.entity.response.DiseaseBannerItem
import com.peekaboo.domain.repository.CrawlRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CrawlRepositoryImpl @Inject constructor(
    private val crawlDataSource: CrawlDataSource,
) : CrawlRepository {

    override suspend fun getTopCrawling(lang: String): Flow<Result<List<DiseaseBannerItem>>> =
        TopCrawlingMapper.responseToModel(apiCall = { crawlDataSource.getTopCrawling(lang) })
}