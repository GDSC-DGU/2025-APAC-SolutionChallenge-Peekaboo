package com.peekaboo.feature

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.peekaboo.domain.entity.request.CreateUserModel
import com.peekaboo.domain.entity.request.DiagnosisModel
import com.peekaboo.navigation.NavRoutes
import com.peekaboo.navigation.diagnosisHistoryNavGraph
import com.peekaboo.navigation.diagnosisNavGraph
import com.peekaboo.navigation.diagnosisQuickNavGraph
import com.peekaboo.navigation.homeNavGraph
import com.peekaboo.navigation.loginNavGraph
import com.peekaboo.navigation.onboardingNavGraph
import com.peekaboo.ui.util.DismissKeyboardOnClick
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {

    val viewModel: MainViewModel = hiltViewModel()
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    val settingUserModel: (CreateUserModel) -> Unit = {
        scope.launch {
            viewModel.userModel.emit(it)
        }
    }
    val settingDiagnosisContent: (DiagnosisModel) -> Unit = {
        scope.launch {
            viewModel.diagnosisContent.emit(it)
        }
    }
    val settingDiagnosisHistory: (Int) -> Unit = {
        scope.launch {
            viewModel.selectedDiagnosisHistory.emit(it)
        }
    }

    DismissKeyboardOnClick {
        Scaffold { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .statusBarsPadding()
            ) {
                NavHost(
                    navController = navController,
                    startDestination = NavRoutes.LogInGraph.route
                ) {
                    loginNavGraph(
                        navController = navController
                    )
                    onboardingNavGraph(
                        navController = navController,
                        setUserModel = settingUserModel,
                        userModel = viewModel.userModel
                    )
                    homeNavGraph(
                        navController = navController
                    )
                    diagnosisNavGraph(
                        navController = navController,
                        setDiagnosisContent = settingDiagnosisContent,
                        diagnosisContent = viewModel.diagnosisContent,
                        selectedDiagnosisHistoryId = viewModel.selectedDiagnosisHistory
                    )
                    diagnosisHistoryNavGraph(
                        navController = navController,
                        setDiagnosisHistoryId = settingDiagnosisHistory
                    )
                    diagnosisQuickNavGraph(
                        navController = navController
                    )
                }
            }
        }
    }
}