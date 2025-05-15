package com.peekaboo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.peekaboo.diagnosis.DiagnosisScreen
import com.peekaboo.diagnosis.explain.SymptomExplainScreen
import com.peekaboo.diagnosis.picture.UploadPictureScreen
import com.peekaboo.diagnosis.selectarea.SelectAreaScreen
import com.peekaboo.diagnosishistory.DiagnosisHistoryScreen
import com.peekaboo.diagnosishistory.detail.DiagnosisHistoryDetailScreen
import com.peekaboo.diagnosisquick.QuickDiagnosisScreen
import com.peekaboo.diagnosisquick.detail.DetailQuickScreen
import com.peekaboo.domain.entity.request.CreateUserModel
import com.peekaboo.domain.entity.request.DiagnosisModel
import com.peekaboo.home.HomeScreen
import com.peekaboo.login.GoogleLogInScreen
import com.peekaboo.onboarding.allergy.AllergyExistScreen
import com.peekaboo.onboarding.diseasehistory.WriteDiseaseHistoryScreen
import com.peekaboo.onboarding.language.LanguageChoiceScreen
import com.peekaboo.onboarding.personal.PersonalInputScreen
import com.peekaboo.onboarding.skin.SkinColorSelectScreen
import kotlinx.coroutines.flow.SharedFlow

fun NavGraphBuilder.loginNavGraph(
    navController: NavController,
) {
    navigation(
        startDestination = NavRoutes.GoogleLogInScreen.route,
        route = NavRoutes.LogInGraph.route
    ) {
        composable(NavRoutes.GoogleLogInScreen.route) {
            GoogleLogInScreen(
                goToOnBoardingPage = {
                    navController.navigate(NavRoutes.LanguageChoiceScreen.route) {
                        popUpTo(0)
                    }
                },
                goToHomePage = {
                    navController.navigate(NavRoutes.HomeScreen.route) {
                        popUpTo(0)
                    }
                }
            )
        }
    }
}

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
    setDiagnosisContent: (DiagnosisModel) -> Unit,
    diagnosisContent: SharedFlow<DiagnosisModel>,
//    selectedDiagnosisHistoryId: SharedFlow<Int>,
    showLanguageBottomSheet: () -> Unit,
    selectedLanguage: SharedFlow<String>,
) {
    navigation(
        startDestination = NavRoutes.SelectAreaScreen.route,
        route = NavRoutes.DiagnosisGraph.route
    ) {
        composable(NavRoutes.SelectAreaScreen.route) {
            SelectAreaScreen(
                goToPicturePage = {
                    setDiagnosisContent(it)
                    navController.navigate(NavRoutes.SelectPictureScreen.route)
                },
                onClickBackBtn = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavRoutes.SelectPictureScreen.route) {
            UploadPictureScreen(
                goToExplainPage = {
                    setDiagnosisContent(it)
                    navController.navigate(NavRoutes.ExplainSymptomScreen.route)
                },
                diagnosisModel = diagnosisContent,
                onClickBackBtn = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavRoutes.ExplainSymptomScreen.route) {
            SymptomExplainScreen(
                goToDiagnosisResultPage = {
                    setDiagnosisContent(it)
                    navController.navigate(NavRoutes.DiagnosisScreen.route)
                },
                diagnosisModel = diagnosisContent,
                onClickBackBtn = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavRoutes.DiagnosisScreen.route) {
            DiagnosisScreen(
                goBackToMain = {
                    navController.navigate(NavRoutes.HomeScreen.route) {
                        popUpTo(0)
                    }
                },
                showLanguageBottomSheet = showLanguageBottomSheet,
                selectedLanguage = selectedLanguage,
                diagnosisContent = diagnosisContent
            )
        }
    }
}

fun NavGraphBuilder.diagnosisHistoryNavGraph(
    navController: NavController,
    setDiagnosisHistoryId: (Int) -> Unit,
    selectedDiagnosisHistoryId: SharedFlow<Int>,
    showLanguageBottomSheet: () -> Unit,
    selectedLanguage: SharedFlow<String>,
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
                    setDiagnosisHistoryId(it)
                    navController.navigate(NavRoutes.DiagnosisHistoryDetailScreen.route)
                },
                onClickBackBtn = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavRoutes.DiagnosisHistoryDetailScreen.route) {
            DiagnosisHistoryDetailScreen(
                goBackToMain = {
                    navController.navigate(NavRoutes.HomeScreen.route) {
                        popUpTo(0)
                    }
                },
                selectedDiagnosisHistoryId = selectedDiagnosisHistoryId,
                showLanguageBottomSheet = showLanguageBottomSheet,
                selectedLanguage = selectedLanguage,
            )
        }
    }
}

fun NavGraphBuilder.diagnosisQuickNavGraph(
    navController: NavController,
    setDiagnosisConstId: (Int) -> Unit,
    diagnosisConstId: SharedFlow<Int>
) {
    navigation(
        startDestination = NavRoutes.DiagnosisQuickScreen.route,
        route = NavRoutes.DiagnosisQuickGraph.route
    ) {
        composable(NavRoutes.DiagnosisQuickScreen.route) {
            QuickDiagnosisScreen(
                goToDetailDiagnosisPage = {
                    setDiagnosisConstId(it)
                    navController.navigate(NavRoutes.DetailQuickScreen.route)
                },
                onClickBackBtn = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavRoutes.DetailQuickScreen.route) {
            DetailQuickScreen(
                goBackToMain = {
                    navController.navigate(NavRoutes.HomeScreen.route) {
                        popUpTo(0)
                    }
                },
                goToDiagnosisPage = {
                    navController.navigate(NavRoutes.SelectAreaScreen.route)
                },
                diagnosisConstId = diagnosisConstId,
                onClickBackBtn = {
                    navController.popBackStack()
                }
            )
        }
    }
}