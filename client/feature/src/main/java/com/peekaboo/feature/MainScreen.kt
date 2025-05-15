package com.peekaboo.feature

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import com.peekaboo.ui.common.bottomsheet.LanguageBottomSheet
import com.peekaboo.ui.common.type.BottomSheetType
import com.peekaboo.ui.util.DismissKeyboardOnClick
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {

    val viewModel: MainViewModel = hiltViewModel()
    val uiState: MainPageState by viewModel.uiState.collectAsStateWithLifecycle()
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

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
    val settingDiagnosisConstId: (Int) -> Unit = {
        scope.launch {
            viewModel.diagnosisConstId.emit(it)
        }
    }

    val showLanguageBottomSheet: () -> Unit = {
        viewModel.setLanguageSelect()
        scope.launch { sheetState.show() }
    }

    DismissKeyboardOnClick {
        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetContent = {
                AnimatedContent(
                    targetState = uiState.bottomSheetType,
                    transitionSpec = {
                        fadeIn(animationSpec = tween(500)) togetherWith fadeOut(
                            animationSpec = tween(
                                500
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .navigationBarsPadding(),
                    label = ""
                ) { currentSheet ->
                    when (currentSheet) {
                        BottomSheetType.LANGUAGE -> {
                            LanguageBottomSheet(
                                selectedLanguage = uiState.selectedLanguage,
                                onSelectLanguage = { viewModel.updateSelectedLanguage(it) },
                                onClickCancel = {
                                    scope.launch {
                                        sheetState.hide()
                                    }
                                },
                                onClickCreate = {
                                    scope.launch {
                                        viewModel.finalSelectedLanguage.emit(uiState.selectedLanguage)
                                        sheetState.hide()
                                    }
                                }
                            )
                        }

                        BottomSheetType.DEFAULT -> {}
                    }
                }
            },
            sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            scrimColor = Color(0, 0, 0, 128)
        ) {
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
                            showLanguageBottomSheet = showLanguageBottomSheet,
                            selectedLanguage = viewModel.finalSelectedLanguage,
                        )
                        diagnosisHistoryNavGraph(
                            navController = navController,
                            setDiagnosisHistoryId = settingDiagnosisHistory,
                            selectedDiagnosisHistoryId = viewModel.selectedDiagnosisHistory,
                            showLanguageBottomSheet = showLanguageBottomSheet,
                            selectedLanguage = viewModel.finalSelectedLanguage
                        )
                        diagnosisQuickNavGraph(
                            navController = navController,
                            setDiagnosisConstId = settingDiagnosisConstId,
                            diagnosisConstId = viewModel.diagnosisConstId
                        )
                    }
                }
            }
        }
    }
}