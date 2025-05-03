package com.peekaboo.onboarding.allergy

import com.peekaboo.domain.entity.request.CreateUserModel
import com.peekaboo.domain.entity.request.InputDescriptionModel
import com.peekaboo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AllergyExistViewModel @Inject constructor(

) : BaseViewModel<AllergyExistPageState>(
    AllergyExistPageState()
) {

    fun onAllergyExistValueChange(changeIndex: Int, newValue: String) {
        val inputList = uiState.value.allergyExistInputList.toMutableList()
        val inputType = InputDescriptionModel(newValue)
        inputList[changeIndex] = inputType

        updateAllergyList(inputList)

        Timber.d("[테스트] -> $inputList")
    }

    fun addAllergyExistList() {
        val inputList = uiState.value.allergyExistInputList.toMutableList()
        inputList.add(InputDescriptionModel())

        updateAllergyList(inputList)
    }

    fun deleteAllergyExist(index: Int) {
        val inputList = uiState.value.allergyExistInputList.toMutableList()
        inputList.removeAt(index)

        updateAllergyList(inputList)
    }

    private fun updateAllergyList(inputList: List<InputDescriptionModel>) {
        updateState(
            uiState.value.copy(
                allergyExistInputList = inputList
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
        uiState.value.userModel.copy(allergyList = uiState.value.allergyExistInputList)
}