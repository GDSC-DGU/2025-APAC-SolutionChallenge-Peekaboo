package com.peekaboo.onboarding.diseasehistory

import com.peekaboo.domain.entity.request.CreateUserModel
import com.peekaboo.domain.entity.request.InputDescriptionModel
import com.peekaboo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WriteDiseaseHistoryViewmodel @Inject constructor(

) : BaseViewModel<WriteDiseaseHistoryPageState>(
    WriteDiseaseHistoryPageState()
) {

    fun onDiseaseHistoryValueChange(changeIndex: Int, newValue: String) {
        val inputList = uiState.value.diseaseHistoryInputList.toMutableList()
        val inputType = InputDescriptionModel(newValue)
        inputList[changeIndex] = inputType

        updateDiseaseHistoryList(inputList)
    }

    fun addDiseaseHistoryList() {
        val inputList = uiState.value.diseaseHistoryInputList.toMutableList()
        inputList.add(InputDescriptionModel())

        updateDiseaseHistoryList(inputList)
    }

    fun deleteDiseaseHistory(index: Int) {
        val inputList = uiState.value.diseaseHistoryInputList.toMutableList()
        inputList.removeAt(index)

        updateDiseaseHistoryList(inputList)
    }

    private fun updateDiseaseHistoryList(inputList: List<InputDescriptionModel>) {
        updateState(
            uiState.value.copy(
                diseaseHistoryInputList = inputList
            )
        )
    }

    fun setUserModel(userModel: CreateUserModel) {
        updateState(
            uiState.value.copy(
                userModel = userModel
            )
        )
    }

    fun updateUserModel() =
        uiState.value.userModel.copy(diseaseList = uiState.value.diseaseHistoryInputList)
}