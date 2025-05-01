package com.peekaboo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.peekaboo.onboarding.language.LanguageChoiceScreen

fun NavGraphBuilder.onboardingGraph(
    navController: NavController,
) {
    navigation(
        startDestination = NavRoutes.LanguageChoiceScreen.route,
        route = NavRoutes.OnBoardingGraph.route
    ) {
        composable(NavRoutes.LanguageChoiceScreen.route) {
            LanguageChoiceScreen()
        }
    }
}