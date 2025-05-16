package com.peekaboo.diagnosis

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.peekaboo.domain.entity.request.DiagnosisModel
import com.peekaboo.ui.common.content.DiagnosisContent
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun DiagnosisScreen(
    goBackToMain: () -> Unit,
    showLanguageBottomSheet: () -> Unit,
    selectedLanguage: SharedFlow<String>,
    diagnosisContent: SharedFlow<DiagnosisModel>,
) {
    val viewModel: DiagnosisViewModel = hiltViewModel()
    val uiState: DiagnosisPageState by viewModel.uiState.collectAsStateWithLifecycle()

    val interactionSource = remember { MutableInteractionSource() }
    val context = LocalContext.current

    LaunchedEffect(selectedLanguage) {
        selectedLanguage.collect {
            viewModel.getDiagnosisPdf(it, context)
        }
    }

    LaunchedEffect(diagnosisContent) {
        diagnosisContent.collect {
            viewModel.getDiagnosisAI(it)
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDiagnosis() {
    DiagnosisContent()
}