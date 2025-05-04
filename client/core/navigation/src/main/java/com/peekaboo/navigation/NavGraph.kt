package com.peekaboo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.peekaboo.diagnosis.DiagnosisScreen
import com.peekaboo.diagnosis.selectarea.SelectAreaScreen
import com.peekaboo.diagnosishistory.DiagnosisHistoryScreen
import com.peekaboo.diagnosisquick.QuickDiagnosisScreen
import com.peekaboo.diagnosisquick.detail.DetailQuickScreen
import com.peekaboo.domain.entity.request.CreateUserModel
import com.peekaboo.home.HomeScreen
import com.peekaboo.onboarding.allergy.AllergyExistScreen
import com.peekaboo.onboarding.diseasehistory.WriteDiseaseHistoryScreen
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
                    navController.navigate(NavRoutes.WriteDiseaseHistoryScreen.route)
                }
            )
        }

        composable(NavRoutes.WriteDiseaseHistoryScreen.route) {
            WriteDiseaseHistoryScreen(
                userModel = userModel,
                goToMainPage = {
                    setUserModel(it)
                    navController.navigate(NavRoutes.HomeScreen.route)
                }
            )
        }
    }
}

fun NavGraphBuilder.homeNavGraph(
    navController: NavController,
) {
    navigation(
        startDestination = NavRoutes.HomeScreen.route,
        route = NavRoutes.HomeGraph.route
    ) {
        composable(NavRoutes.HomeScreen.route) {
            HomeScreen(
                goToDiagnosisSelectAreaPage = {
                    navController.navigate(NavRoutes.SelectAreaScreen.route)
                },
                goToDiagnosisHistoryPage = {
                    navController.navigate(NavRoutes.DiagnosisHistoryScreen.route)
                },
                goToDiagnosisQuickPage = {
                    navController.navigate(NavRoutes.DiagnosisQuickScreen.route)
                }
            )
        }
    }
}

fun NavGraphBuilder.diagnosisNavGraph(
    navController: NavController,
) {
    navigation(
        startDestination = NavRoutes.DiagnosisScreen.route,
        route = NavRoutes.DiagnosisGraph.route
    ) {
        composable(NavRoutes.DiagnosisScreen.route) {
            DiagnosisScreen()
        }

        composable(NavRoutes.SelectAreaScreen.route) {
            SelectAreaScreen()
        }
    }
}

fun NavGraphBuilder.diagnosisHistoryNavGraph(
    navController: NavController,
) {
    navigation(
        startDestination = NavRoutes.DiagnosisHistoryScreen.route,
        route = NavRoutes.DiagnosisHistoryGraph.route
    ) {
        composable(NavRoutes.DiagnosisHistoryScreen.route) {
            DiagnosisHistoryScreen(
                goToDiagnosisPage = {
                    navController.navigate(NavRoutes.SelectAreaScreen.route)
                },
                goToDiagnosisResultPage = {
                    navController.navigate(NavRoutes.DiagnosisScreen.route)
                }
            )
        }
    }
}

fun NavGraphBuilder.diagnosisQuickNavGraph(
    navController: NavController,
) {
    navigation(
        startDestination = NavRoutes.DiagnosisQuickScreen.route,
        route = NavRoutes.DiagnosisQuickGraph.route
    ) {
        composable(NavRoutes.DiagnosisQuickScreen.route) {
            QuickDiagnosisScreen(
                goToDetailDiagnosisPage = {
                    navController.navigate(NavRoutes.DetailQuickScreen.route)
                }
            )
        }

        composable(NavRoutes.DetailQuickScreen.route) {
            DetailQuickScreen()
        }
    }
}