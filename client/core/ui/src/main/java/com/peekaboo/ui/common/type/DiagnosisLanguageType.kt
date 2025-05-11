package com.peekaboo.ui.common.type

enum class DiagnosisLanguageType(
    val language: String,
    val languageApi: String,
) {
    KOREAN("한국어", "ko"),
    ENGLISH("English", "en"),
    CHINESE("中文", "ch"),
    JAPANESE("日本語", "ja");
}