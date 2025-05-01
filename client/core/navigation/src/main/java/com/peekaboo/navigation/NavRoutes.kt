package com.peekaboo.navigation

sealed class NavRoutes(val route: String) {

    data object OnBoardingGraph: NavRoutes("onboarding_graph")
    data object LanguageChoiceScreen: NavRoutes("language_choice")
}