package com.peekaboo.ui.common.type

enum class RatingType (
    val rating: Int,
    val ratingText: String
) {
    FOURTH(4, "prevention"),
    THIRD(3, "sanitary control"),
    SECOND(2, "vaccination"),
    FIRST(1, "immediate isolation");

    companion object {
        fun getRatingText(rating: Int): String =
            entries.firstOrNull { it.rating == rating }?.ratingText ?: ""
    }
}