package com.peekaboo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.peekaboo.onboarding.language.LanguageChoiceScreen
import com.peekaboo.onboarding.personal.PersonalInputScreen

fun NavGraphBuilder.onboardingNavGraph(
    navController: NavController,
) {
    navigation(
        startDestination = NavRoutes.LanguageChoiceScreen.route,
        route = NavRoutes.OnBoardingGraph.route
    ) {
        composable(NavRoutes.LanguageChoiceScreen.route) {
            LanguageChoiceScreen()
        }

        composable(NavRoutes.PersonalInputScreen.route) {
            PersonalInputScreen()
        }
    }
}