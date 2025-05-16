package com.peekaboo.navigation

sealed class NavRoutes(val route: String) {

    data object LogInGraph: NavRoutes("login_graph")
    data object GoogleLogInScreen: NavRoutes("google_login")

    data object OnBoardingGraph: NavRoutes("onboarding_graph")
    data object LanguageChoiceScreen: NavRoutes("language_choice")
    data object PersonalInputScreen: NavRoutes("personal_input")
    data object SkinColorSelectScreen: NavRoutes("skin_color_select")
    data object WriteDiseaseHistoryScreen: NavRoutes("disease_history")
    data object AllergyExistScreen: NavRoutes("allergy_exist")

    data object HomeGraph: NavRoutes("home_graph")
    data object HomeScreen: NavRoutes("home")

    data object DiagnosisGraph: NavRoutes("diagnosis_graph")
    data object DiagnosisScreen: NavRoutes("diagnosis")
    data object SelectAreaScreen: NavRoutes("select_area")
    data object SelectPictureScreen: NavRoutes("select_picture")
    data object ExplainSymptomScreen: NavRoutes("explain_symptom")

    data object DiagnosisHistoryGraph: NavRoutes("diagnosis_history_graph")
    data object DiagnosisHistoryScreen: NavRoutes("diagnosis_history")
    data object DiagnosisHistoryDetailScreen: NavRoutes("diagnosis_history_detail")

    data object DiagnosisQuickGraph: NavRoutes("diagnosis_quick_graph")
    data object DiagnosisQuickScreen: NavRoutes("diagnosis_quick")
    data object DetailQuickScreen: NavRoutes("detail_quick")
}