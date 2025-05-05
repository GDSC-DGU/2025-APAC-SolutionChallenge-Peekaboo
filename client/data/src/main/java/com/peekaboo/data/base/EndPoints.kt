package com.peekaboo.data.base

object EndPoints {

    object Auth {
        const val LOGIN = "/oauth/login/google"
    }

    object Diagnosis {
        private const val DIAGNOSIS = "api/v1/diagnosis"
        const val HISTORY = "$DIAGNOSIS/history"
    }
}