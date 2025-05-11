package com.peekaboo.ui.common.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.CancelDiagnosis
import com.peekaboo.design_system.CreateDiagnosis
import com.peekaboo.design_system.DiagnosingChooseLanguage
import com.peekaboo.design_system.Gray1
import com.peekaboo.design_system.Gray3
import com.peekaboo.design_system.Main2
import com.peekaboo.design_system.R
import com.peekaboo.design_system.White3
import com.peekaboo.ui.common.button.BottomTwoTypeBtn
import com.peekaboo.ui.common.type.DiagnosisLanguageType

@Composable
fun LanguageBottomSheet(
    selectedLanguage: String,
    onSelectLanguage: (String) -> Unit,
    onClickCancel: () -> Unit,
    onClickCreate: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    LanguageListContent(
        interactionSource = interactionSource,
        selectedLanguage = selectedLanguage,
        onSelectLanguage = onSelectLanguage,
        onClickCancel = onClickCancel,
        onClickCreate = onClickCreate
    )
}

@Composable
fun LanguageListContent(
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    selectedLanguage: String = "",
    onSelectLanguage: (String) -> Unit = {},
    onClickCancel: () -> Unit = {},
    onClickCreate: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White3),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = DiagnosingChooseLanguage,
            color = Black1,
            style = BaeBaeTypo.Head3,
            modifier = Modifier
                .padding(top = 30.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        LanguageList(
            interactionSource = interactionSource,
            selectedLanguage = selectedLanguage,
            onSelectLanguage = onSelectLanguage
        )

        BottomBtn(
            onClickCancel = onClickCancel,
            onClickCreate = onClickCreate
        )
    }
}

@Composable
fun BottomBtn(
    onClickCancel: () -> Unit,
    onClickCreate: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, bottom = 20.dp)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BottomTwoTypeBtn(
            itemText = CancelDiagnosis,
            modifier = Modifier.weight(1f),
            isItemPositive = false,
            onClickAction = onClickCancel
        )

        BottomTwoTypeBtn(
            itemText = CreateDiagnosis,
            modifier = Modifier.weight(1f),
            isItemPositive = true,
            onClickAction = onClickCreate
        )
    }
}

@Composable
fun LanguageList(
    interactionSource: MutableInteractionSource,
    selectedLanguage: String,
    onSelectLanguage: (String) -> Unit,
) {
    DiagnosisLanguageType.entries.forEach { language ->
        LanguageListItem(
            interactionSource = interactionSource,
            language = language.language,
            isSelected = (language.languageApi == selectedLanguage),
            onSelect = { onSelectLanguage(language.languageApi) }
        )
    }
}

@Composable
fun LanguageListItem(
    interactionSource: MutableInteractionSource,
    isSelected: Boolean = false,
    language: String,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isSelected) Gray1 else White3)
            .clickable(
                onClick = onSelect,
                interactionSource = interactionSource,
                indication = null
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = language,
            color = if (isSelected) Main2 else Gray3,
            style = BaeBaeTypo.Body3,
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .weight(1f)
        )

        if (isSelected) {
            Image(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = "check",
                modifier = Modifier
                    .padding(end = 20.dp)
                    .size(20.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLanguage() {
    LanguageListContent()
}