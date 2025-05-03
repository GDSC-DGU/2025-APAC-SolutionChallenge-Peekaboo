package com.peekaboo.navigation

sealed class NavRoutes(val route: String) {


    data object OnBoardingGraph: NavRoutes("onboarding_graph")
    data object LanguageChoiceScreen: NavRoutes("language_choice")
    data object PersonalInputScreen: NavRoutes("personal_input")
    data object SkinColorSelectScreen: NavRoutes("skin_color_select")
    data object DiseaseHistoryScreen: NavRoutes("disease_history")
    data object AllergyExistScreen: NavRoutes("allergy_exist")

    data object HomeGraph: NavRoutes("home_graph")
    data object HomeScreen: NavRoutes("home")

    data object DiagnosisGraph: NavRoutes("diagnosis_graph")
    data object DiagnosisScreen: NavRoutes("diagnosis")

    data object DiagnosisHistoryGraph: NavRoutes("diagnosis_history_graph")
    data object DiagnosisHistoryScreen: NavRoutes("diagnosis_history")
}