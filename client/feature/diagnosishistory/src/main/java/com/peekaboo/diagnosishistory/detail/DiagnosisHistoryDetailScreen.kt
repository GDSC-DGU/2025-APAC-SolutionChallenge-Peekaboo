package com.peekaboo.diagnosishistory.detail

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.peekaboo.ui.common.content.DiagnosisContent
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun DiagnosisHistoryDetailScreen(
    goBackToMain: () -> Unit,
    selectedDiagnosisHistoryId: SharedFlow<Int>,
    showLanguageBottomSheet: () -> Unit,
    selectedLanguage: SharedFlow<String>,
//    diagnosisContent: SharedFlow<DiagnosisModel>,
) {
    val viewModel: DiagnosisHistoryDetailViewModel = hiltViewModel()
    val uiState: DiagnosisHistoryDetailPageState by viewModel.uiState.collectAsStateWithLifecycle()

    val interactionSource = remember { MutableInteractionSource() }
    val context = LocalContext.current

    LaunchedEffect(selectedDiagnosisHistoryId) {
        selectedDiagnosisHistoryId.collect {
            viewModel.setDiagnosisResult(it)
        }
    }

    LaunchedEffect(selectedLanguage) {
        selectedLanguage.collect {
            viewModel.getDiagnosisPdf(it, context)
        }
    }

    DiagnosisContent(
        interactionSource = interactionSource,
        onSelectDisease = { viewModel.setSelectedDisease(it) },
        selectedDisease = uiState.selectedDisease,
        onClickBackToMain = { goBackToMain() },
        diseaseTotal = uiState.diseaseTotal,
        customDescription = uiState.customDescription,
        firstDiseaseModel = uiState.firstDiseaseModel,
        secondDiseaseModel = uiState.secondDiseaseModel,
        thirdDiseaseModel = uiState.thirdDiseaseModel,
        onClickDiagnosisBtn = {
            showLanguageBottomSheet()
        },
        isDataUpdateSuccess = uiState.isDataUpdateSuccess
    )
}