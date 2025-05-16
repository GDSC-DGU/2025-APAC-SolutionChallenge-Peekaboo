package com.peekaboo.diagnosis.picture

import android.content.Context
import android.net.Uri
import com.peekaboo.domain.entity.request.DiagnosisModel
import com.peekaboo.multipart.UriUtil
import com.peekaboo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UploadPictureViewModel @Inject constructor(

) : BaseViewModel<UploadPicturePageState>(
    UploadPicturePageState()
) {

    fun setDiagnosisContent(diagnosis: DiagnosisModel) {
        updateState(
            uiState.value.copy(
                diagnosisContent = diagnosis
            )
        )
    }

    fun setSelectedImg(context: Context, img: Uri?) {
        updateState(
            uiState.value.copy(
                selectedImg = img?.let { UriUtil.uriToFile(context, it) }
            )
        )
    }

    fun updateDiagnosisContent() =
        uiState.value.diagnosisContent.copy(photo = uiState.value.selectedImg)
}