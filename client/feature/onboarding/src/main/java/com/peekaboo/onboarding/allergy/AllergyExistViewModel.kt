package com.peekaboo.onboarding.allergy

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
        inputList[changeIndex] = newValue

        updateAllergyList(inputList)

        Timber.d("[테스트] -> $inputList")
    }

    fun addAllergyExistList() {
        val inputList = uiState.value.allergyExistInputList.toMutableList()
        inputList.add("")

        updateAllergyList(inputList)
    }

    private fun updateAllergyList(inputList: List<String>) {
        updateState(
            uiState.value.copy(
                allergyExistInputList = inputList
            )
        )
    }
}