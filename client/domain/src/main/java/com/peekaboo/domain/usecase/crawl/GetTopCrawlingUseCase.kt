package com.peekaboo.domain.usecase.crawl

import com.peekaboo.domain.base.UseCase
import com.peekaboo.domain.entity.response.DiseaseBannerItem
import com.peekaboo.domain.repository.CrawlRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopCrawlingUseCase @Inject constructor(
    private val crawlRepository: CrawlRepository,
) : UseCase<String, Result<List<DiseaseBannerItem>>>() {

    override suspend fun invoke(request: String): Flow<Result<List<DiseaseBannerItem>>> =
        crawlRepository.getTopCrawling(request)
}