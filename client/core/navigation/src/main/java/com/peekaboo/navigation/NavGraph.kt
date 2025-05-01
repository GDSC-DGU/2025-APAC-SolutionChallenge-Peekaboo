package com.peekaboo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.peekaboo.domain.entity.request.CreateUserModel
import com.peekaboo.onboarding.allergy.AllergyExistScreen
import com.peekaboo.onboarding.diseasehistory.DiseaseHistoryScreen
import com.peekaboo.onboarding.language.LanguageChoiceScreen
import com.peekaboo.onboarding.personal.PersonalInputScreen
import com.peekaboo.onboarding.skin.SkinColorSelectScreen
import kotlinx.coroutines.flow.SharedFlow

fun NavGraphBuilder.onboardingNavGraph(
    navController: NavController,
    setUserModel: (CreateUserModel) -> Unit,
    userModel: SharedFlow<CreateUserModel>
) {
    navigation(
        startDestination = NavRoutes.LanguageChoiceScreen.route,
        route = NavRoutes.OnBoardingGraph.route
    ) {
        composable(NavRoutes.LanguageChoiceScreen.route) {
            LanguageChoiceScreen(
                goToPersonalInputPage = {
                    navController.navigate(NavRoutes.PersonalInputScreen.route)
                }
            )
        }

        composable(NavRoutes.PersonalInputScreen.route) {
            PersonalInputScreen()
        }

        composable(NavRoutes.SkinColorSelectScreen.route) {
            SkinColorSelectScreen()
        }

        composable(NavRoutes.AllergyExistScreen.route) {
            AllergyExistScreen()
        }

        composable(NavRoutes.DiseaseHistoryScreen.route) {
            DiseaseHistoryScreen()
        }
    }
}