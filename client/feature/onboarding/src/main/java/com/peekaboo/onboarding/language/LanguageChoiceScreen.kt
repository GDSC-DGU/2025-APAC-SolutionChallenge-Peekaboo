package com.peekaboo.onboarding.language

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.LanguageChoiceSemiTitle
import com.peekaboo.design_system.LanguageChoiceTitle
import com.peekaboo.design_system.LocationChoiceSemiTitle
import com.peekaboo.design_system.Next
import com.peekaboo.design_system.OnBoardingTitle
import com.peekaboo.design_system.White3
import com.peekaboo.domain.entity.request.CreateUserModel
import com.peekaboo.onboarding.type.LanguageType
import com.peekaboo.onboarding.type.LocationType
import com.peekaboo.ui.common.appbar.TopBar
import com.peekaboo.ui.common.button.BottomRectangleBtn
import com.peekaboo.ui.common.content.CourseNumber
import com.peekaboo.ui.common.item.SelectItem

@Composable
fun LanguageChoiceScreen(
    goToPersonalInputPage: (CreateUserModel) -> Unit,
) {

    val viewModel: LanguageChoiceViewModel = hiltViewModel()
    val uiState: LanguageChoicePageState by viewModel.uiState.collectAsStateWithLifecycle()

    LanguageChoiceContent(
        selectedLocation = uiState.selectedLocation,
        onSelectLocation = { location ->
            viewModel.setSelectedLocation(location)
        },
        selectedLanguage = uiState.selectedLanguage,
        onSelectLanguage = { language ->
            viewModel.setSelectedLanguage(language)
        },
        onClickBtnAction = {
            goToPersonalInputPage(viewModel.setUserModel())
        }
    )
}

@Composable
fun LanguageChoiceContent(
    selectedLocation: String = "",
    onSelectLocation: (String) -> Unit = {},
    selectedLanguage: String = "",
    onSelectLanguage: (String) -> Unit = {},
    onClickBtnAction: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White3)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            TopBar(
                titleText = OnBoardingTitle
            )

            CourseNumber(
                currentNumber = 1,
                totalNumber = 5,
                paddingTop = 35,
                paddingStart = 20
            )

            Text(
                text = LanguageChoiceTitle,
                color = Black1,
                style = BaeBaeTypo.Body1,
                modifier = Modifier
                    .padding(top = 10.dp, start = 20.dp)
            )

            LocationChoiceBox(
                selectedLocation = selectedLocation,
                onSelectLocation = onSelectLocation
            )

            LanguageChoiceBox(
                selectedLanguage = selectedLanguage,
                onSelectLanguage = onSelectLanguage
            )
        }

        BottomRectangleBtn(
            horizontalPadding = 20,
            btnText = Next,
            isBtnValid = (selectedLanguage.isNotEmpty() && selectedLocation.isNotEmpty()),
            onClickAction = onClickBtnAction
        )

        Spacer(modifier = Modifier.height(36.dp))
    }
}

@Composable
fun LocationChoiceBox(
    selectedLocation: String,
    onSelectLocation: (String) -> Unit,
) {
    Text(
        text = LocationChoiceSemiTitle,
        color = Black1,
        style = BaeBaeTypo.Body3,
        modifier = Modifier
            .padding(top = 60.dp, start = 20.dp)
    )

    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 17.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LocationType.entries.forEach { location ->
            SelectItem(
                itemText = location.location,
                modifier = Modifier.weight(1f),
                onSelectItem = { onSelectLocation(location.locationApi) },
                isItemSelected = (location.locationApi == selectedLocation)
            )
        }
    }
}

@Composable
fun LanguageChoiceBox(
    selectedLanguage: String,
    onSelectLanguage: (String) -> Unit,
) {
    Text(
        text = LanguageChoiceSemiTitle,
        color = Black1,
        style = BaeBaeTypo.Body3,
        modifier = Modifier
            .padding(top = 18.dp, start = 20.dp)
    )

    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 17.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LanguageType.entries.forEach { language ->
            SelectItem(
                itemText = language.language,
                modifier = Modifier.weight(1f),
                onSelectItem = { onSelectLanguage(language.languageApi) },
                isItemSelected = (language.languageApi == selectedLanguage)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLanguageChoice() {
    LanguageChoiceContent()
}