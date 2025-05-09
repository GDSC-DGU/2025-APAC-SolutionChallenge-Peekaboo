package com.peekaboo.data.entity.response.crawl

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CrawlResponseDto (
    @SerialName("weeklyInfo")
    val weeklyInfo: List<CrawlingItem> = emptyList()
) {
    @Serializable
    data class CrawlingItem(
        @SerialName("name")
        val name: String = "",
        @SerialName("location")
        val location: String = "",
        @SerialName("description")
        val description: String = ""
    )
}