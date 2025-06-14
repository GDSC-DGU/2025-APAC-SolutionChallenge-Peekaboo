package com.peekaboo.data.base

object EndPoints {

    object Auth {
        const val LOGIN = "/oauth/login/google"
        const val USER = "/api/v1/users"
    }

    object Diagnosis {
        private const val DIAGNOSIS = "api/v1/diagnosis"
        const val HISTORY = "$DIAGNOSIS/history"
        const val HISTORYDETAIL = "$HISTORY/{historyId}"
        const val HISTORYCONST = "$DIAGNOSIS/const/{constId}"
        const val DIAGNOSISPDF = "/api/v1/diagnosis/pdf/{diagnosisId}"
        const val DIAGNOSISAI = DIAGNOSIS
    }

    object Crawling {
        const val TOPBANNER = "."
    }
}