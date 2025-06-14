package com.peekaboo.login

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.peekaboo.design_system.BaeBae
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.HomeSemiTitle
import com.peekaboo.design_system.Main2
import com.peekaboo.design_system.R
import com.peekaboo.design_system.White3

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun GoogleLogInScreen(
    goToOnBoardingPage: () -> Unit,
    goToHomePage: () -> Unit,
) {
    val viewModel: GoogleLogInViewModel = hiltViewModel()
    val context = LocalContext.current
    val activity: Activity = context as Activity

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is GoogleLogInEvent.GoToOnBoardingPage -> {
                    goToOnBoardingPage()
                }

                is GoogleLogInEvent.GoToHomePage -> {
                    goToHomePage()
                }
            }
        }
    }

    GoogleLogInContent(
        onClickGoogleLogIn = { viewModel.startGoogleLogIn(activity) }
    )
}

@Composable
fun GoogleLogInContent(
    onClickGoogleLogIn: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White3),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = BaeBae,
            color = Black1,
            style = BaeBaeTypo.Head1,
            modifier = Modifier
                .padding(top = 144.dp)
        )

        Text(
            text = buildAnnotatedString {
                append(HomeSemiTitle)
                withStyle(style = SpanStyle(color = Main2)) {
                    append(BaeBae)
                }
            },
            color = Black1,
            style = BaeBaeTypo.Body1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 40.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "logo",
            modifier = Modifier
                .padding(top = 128.dp)
                .size(127.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.ic_google_login),
            contentDescription = "google login",
            modifier = Modifier
                .padding(bottom = 80.dp)
                .clickable(
                    onClick = onClickGoogleLogIn
                )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewGoogleLogIn() {
    GoogleLogInContent()
}