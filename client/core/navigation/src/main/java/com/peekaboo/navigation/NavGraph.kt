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
    userModel: SharedFlow<CreateUserModel>,
) {
    navigation(
        startDestination = NavRoutes.LanguageChoiceScreen.route,
        route = NavRoutes.OnBoardingGraph.route
    ) {
        composable(NavRoutes.LanguageChoiceScreen.route) {
            LanguageChoiceScreen(
                goToPersonalInputPage = {
                    setUserModel(it)
                    navController.navigate(NavRoutes.PersonalInputScreen.route)
                }
            )
        }

        composable(NavRoutes.PersonalInputScreen.route) {
            PersonalInputScreen(
                userModel = userModel,
                goToSkinSelectPage = {
                    setUserModel(it)
                    navController.navigate(NavRoutes.SkinColorSelectScreen.route)
                }
            )
        }

        composable(NavRoutes.SkinColorSelectScreen.route) {
            SkinColorSelectScreen(
                userModel = userModel,
                goToAllergyPage = {
                    setUserModel(it)
                    navController.navigate(NavRoutes.AllergyExistScreen.route)
                }
            )
        }

        composable(NavRoutes.AllergyExistScreen.route) {
            AllergyExistScreen(
                userModel = userModel,
                goToDiseaseHistoryPage = {
                    setUserModel(it)
                    navController.navigate(NavRoutes.DiseaseHistoryScreen.route)
                }
            )
        }

        composable(NavRoutes.DiseaseHistoryScreen.route) {
            DiseaseHistoryScreen(
                userModel = userModel,
                goToMainPage = {
                    setUserModel(it)
                }
            )
        }
    }
}