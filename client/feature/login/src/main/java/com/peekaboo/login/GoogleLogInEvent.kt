package com.peekaboo.login

import com.peekaboo.ui.base.Event

sealed class GoogleLogInEvent: Event {
    data object GoToOnBoardingPage: GoogleLogInEvent()
    data object GoToHomePage: GoogleLogInEvent()
}