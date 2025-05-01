package com.peekaboo.feature

import com.peekaboo.ui.base.BaseViewModel
import com.peekaboo.ui.base.PageState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

): BaseViewModel<PageState.Default>(
    PageState.Default
) {
}