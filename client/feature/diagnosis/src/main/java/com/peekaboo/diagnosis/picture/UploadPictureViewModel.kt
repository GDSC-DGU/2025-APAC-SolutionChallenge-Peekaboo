package com.peekaboo.diagnosis.picture

import com.peekaboo.domain.entity.request.DiagnosisModel
import com.peekaboo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
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

    fun setSelectedImg(img: File) {
        updateState(
            uiState.value.copy(
                selectedImg = img
            )
        )
    }

    fun updateDiagnosisContent() =
        uiState.value.diagnosisContent.copy(photo = uiState.value.selectedImg)
}