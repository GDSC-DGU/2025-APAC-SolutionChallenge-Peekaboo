package com.peekaboo.diagnosisquick

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.Gray1
import com.peekaboo.design_system.QuickDiagnosisSemiTitle
import com.peekaboo.design_system.QuickDiagnosisTitle
import com.peekaboo.design_system.R
import com.peekaboo.design_system.White2
import com.peekaboo.design_system.White3
import com.peekaboo.ui.common.appbar.TopBar
import com.peekaboo.ui.common.type.DiseaseType

@Composable
fun QuickDiagnosisScreen(
    goToDetailDiagnosisPage: (Int) -> Unit,
) {
    QuickDiagnosisContent(
        onClickDisease = { id -> goToDetailDiagnosisPage(id) }
    )
}

@Composable
fun QuickDiagnosisContent(
    onClickDisease: (Int) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White3)
    ) {
        TopBar(
            titleText = QuickDiagnosisTitle,
            isIconValid = true
        )

        Text(
            text = QuickDiagnosisSemiTitle,
            color = Black1,
            style = BaeBaeTypo.Body3,
            modifier = Modifier
                .padding(top = 46.dp, start = 20.dp)
        )

        SymptomList(
            onClickDisease = onClickDisease
        )
    }
}

@Composable
fun SymptomList(
    onClickDisease: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 24.dp)
            .padding(bottom = 30.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(DiseaseType.entries) { disease ->
            SymptomItem(
                diseaseName = disease.diseaseNameEng,
                diseaseImg = disease.diseaseImg,
                onClickAction = { onClickDisease(disease.id) }
            )
        }
    }
}

@Composable
fun SymptomItem(
    diseaseName: String,
    diseaseImg: Int,
    onClickAction: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(White2)
            .border(1.dp, color = Gray1, RoundedCornerShape(5.dp))
            .clickable(
                onClick = onClickAction
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = diseaseImg),
            contentDescription = "disease",
            modifier = Modifier
                .padding(vertical = 15.dp)
                .padding(start = 15.dp)
                .size(width = 35.dp, height = 40.dp)
                .clip(RoundedCornerShape(25.dp))
        )

        Text(
            text = diseaseName,
            color = Black1,
            style = BaeBaeTypo.Caption1,
            modifier = Modifier
                .padding(vertical = 28.dp, horizontal = 17.dp)
                .weight(1f)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_arrow_next),
            contentDescription = "arrow next",
            modifier = Modifier
                .padding(end = 4.dp)
                .size(24.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewQuickDiagnosis() {
    QuickDiagnosisContent()
}