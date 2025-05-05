package com.peekaboo.login

import android.app.Activity
import androidx.lifecycle.viewModelScope
import com.peekaboo.domain.entity.response.TokenStoreModel
import com.peekaboo.domain.entity.response.auth.LogInResponseModel
import com.peekaboo.domain.usecase.auth.PostLogInUseCase
import com.peekaboo.domain.usecase.auth.SaveTokenUseCase
import com.peekaboo.ui.base.BaseViewModel
import com.peekaboo.ui.base.PageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GoogleLogInViewModel @Inject constructor(
    private val saveTokenUseCase: SaveTokenUseCase,
    private val postLogInUseCase: PostLogInUseCase
) : BaseViewModel<PageState.Default>(PageState.Default) {

    fun startGoogleLogIn(activity: Activity) {
        viewModelScope.launch {
            try {
                val token = GoogleLoginManager.signInWithGoogle(activity)

                if (token != null) {
                    Timber.d("[로그인] 성공 -> $token")
                    saveTokenUseCase(
                        request = TokenStoreModel(
                            accessToken = token,
                            refreshToken = ""
                        )
                    ).collect { resultResponse(it, {}) }

                    postLogIn()
                }
            } catch (e: Exception) {
                Timber.d("[로그인] 실패")
            }
        }
    }

    private fun postLogIn() {
        viewModelScope.launch {
            postLogInUseCase(request = Unit).collect {
                resultResponse(it, ::onSuccessGoogleLogIn)
            }
        }
    }

    private fun onSuccessGoogleLogIn(data: LogInResponseModel) {
        viewModelScope.launch {
            saveTokenUseCase(
                request = TokenStoreModel(
                    accessToken = data.jwtTokenDto.accessToken,
                    refreshToken = data.jwtTokenDto.refreshToken
                )
            ).collect { resultResponse(it, {}) }

            //
        }
    }

}