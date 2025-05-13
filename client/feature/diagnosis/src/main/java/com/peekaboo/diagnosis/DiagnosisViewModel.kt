package com.peekaboo.diagnosis

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.viewModelScope
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryDetailModel
import com.peekaboo.domain.usecase.diagnosis.GetDiagnosisHistoryDetailUseCase
import com.peekaboo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DiagnosisViewModel @Inject constructor(
    private val getDiagnosisHistoryDetailUseCase: GetDiagnosisHistoryDetailUseCase,
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
        viewModelScope.launch {
            getDiagnosisHistoryDetailUseCase(request = historyId).collect {
                resultResponse(it, ::onSuccessDiagnosisHistoryDetail)
            }
        }
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

    fun setSelectedLanguage(language: String, context: Context) {
        Timber.d("[language] -> $language")

        // TODO 진단하기 서버통신
        val url = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"

        downloadPdf(context, url)
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

}