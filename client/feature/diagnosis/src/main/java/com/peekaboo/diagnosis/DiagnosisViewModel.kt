package com.peekaboo.diagnosis

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.viewModelScope
import com.peekaboo.domain.entity.request.DiagnosisModel
import com.peekaboo.domain.entity.request.ImageModel
import com.peekaboo.domain.entity.request.diagnosis.DiagnosisAIRequestModel
import com.peekaboo.domain.entity.request.diagnosis.DiagnosisPdfRequestModel
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryDetailModel
import com.peekaboo.domain.usecase.diagnosis.GetDiagnosisAIUseCase
import com.peekaboo.domain.usecase.diagnosis.GetDiagnosisHistoryDetailUseCase
import com.peekaboo.domain.usecase.diagnosis.GetDiagnosisPdfUseCase
import com.peekaboo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiagnosisViewModel @Inject constructor(
    private val getDiagnosisHistoryDetailUseCase: GetDiagnosisHistoryDetailUseCase,
    private val getDiagnosisPdfUseCase: GetDiagnosisPdfUseCase,
    private val getDiagnosisAIUseCase: GetDiagnosisAIUseCase
) : BaseViewModel<DiagnosisPageState>(
    DiagnosisPageState()
) {

    fun setSelectedDisease(disease: String) {
        updateState(
            uiState.value.copy(
                selectedDisease = disease
            )
        )
    }

    fun setDiagnosisResult(historyId: Int) {
        setDiagnosisId(historyId)

        viewModelScope.launch {
            getDiagnosisHistoryDetailUseCase(request = historyId).collect {
                resultResponse(it, ::onSuccessDiagnosisHistoryDetail)
            }
        }
    }

    private fun setDiagnosisId(id: Int) {
        updateState(
            uiState.value.copy(
                diagnosisId = id
            )
        )
    }

    private fun onSuccessDiagnosisHistoryDetail(data: DiagnosisHistoryDetailModel) {
        val firstDisease = matchDiseaseNumber(1, data)
        val secondDisease = matchDiseaseNumber(2, data)
        val thirdDisease = matchDiseaseNumber(3, data)

        updateState(
            uiState.value.copy(
                customDescription = data.customDescription,
                selectedDisease = firstDisease.diseaseName,
                diseaseTotal = data.diseaseList,
                firstDiseaseModel = firstDisease,
                secondDiseaseModel = secondDisease,
                thirdDiseaseModel = thirdDisease
            )
        )
    }

    private fun matchDiseaseNumber(ranking: Int, data: DiagnosisHistoryDetailModel) =
        data.diseaseList.firstOrNull { it.ranking == ranking }
            ?: DiagnosisHistoryDetailModel.DiseaseDetailItem()

    fun getDiagnosisPdf(language: String, context: Context) {
        val diagnosisId = uiState.value.diagnosisId

        viewModelScope.launch {
            getDiagnosisPdfUseCase(DiagnosisPdfRequestModel(diagnosisId, language)).collect {
                resultResponse(it, { data ->
                    downloadPdf(context, data)
                })
            }
        }
    }

    private fun downloadPdf(context: Context, url: String, title: String = "Diagnosis") {
        val request = DownloadManager.Request(Uri.parse(url)).apply {
            setTitle(title)
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "${title}.pdf")
            setAllowedOverMetered(true)
            setAllowedOverRoaming(true)
        }

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }

    fun getDiagnosisAI(diagnosis: DiagnosisModel) {
        viewModelScope.launch {
            getDiagnosisAIUseCase(
                DiagnosisAIRequestModel(
                    area = diagnosis.selectedArea,
                    symptoms = diagnosis.symptomsExplain,
                    image = ImageModel(diagnosis.photo, IMAGE)
                )
            ).collect {
                resultResponse(it, ::onSuccessDiagnosisHistoryDetail)
            }
        }
    }

    companion object {
        const val IMAGE = "image"
    }
}