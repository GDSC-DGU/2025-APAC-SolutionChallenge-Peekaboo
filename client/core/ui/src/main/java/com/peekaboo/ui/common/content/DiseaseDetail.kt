package com.peekaboo.ui.common.content

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.peekaboo.design_system.BaeBaeTypo
import com.peekaboo.design_system.Black1
import com.peekaboo.design_system.Gray1
import com.peekaboo.design_system.Gray3
import com.peekaboo.design_system.Main2
import com.peekaboo.design_system.QuickDiseaseFirstAid
import com.peekaboo.design_system.QuickDiseaseFirstAidMild
import com.peekaboo.design_system.QuickDiseaseFirstAidSevere
import com.peekaboo.design_system.QuickDiseaseMedicine
import com.peekaboo.design_system.QuickDiseasePrecautions
import com.peekaboo.design_system.QuickDiseasePreventiveMeasure
import com.peekaboo.design_system.QuickDiseaseSymptomsCategory
import com.peekaboo.design_system.QuickDiseaseSymptomsRating
import com.peekaboo.design_system.QuickDiseaseSymptomsReason
import com.peekaboo.design_system.QuickDiseaseSymptomsSite
import com.peekaboo.design_system.QuickDiseaseSymptomsTitle
import com.peekaboo.design_system.QuickDiseaseSymptomsType
import com.peekaboo.design_system.White3
import com.peekaboo.domain.entity.response.diagnosis.DiagnosisHistoryDetailModel
import com.peekaboo.domain.entity.response.diagnosis.DrugItem
import com.peekaboo.domain.entity.response.diagnosis.SymptomItem

@Composable
fun DiseaseDetail(
    isDetailDescriptionValid: Boolean = false,
    diseaseModel: DiagnosisHistoryDetailModel.DiseaseDetailItem = DiagnosisHistoryDetailModel.DiseaseDetailItem(),
) {
    DiseaseDetailContent(
        isDetailDescriptionValid = isDetailDescriptionValid,
        diseaseModel = diseaseModel
    )
}

@Composable
fun DiseaseDetailContent(
    isDetailDescriptionValid: Boolean = false,
    diseaseModel: DiagnosisHistoryDetailModel.DiseaseDetailItem = DiagnosisHistoryDetailModel.DiseaseDetailItem(),
) {
    if (isDetailDescriptionValid) {
        DiseaseDetailDescription(
            diseaseName = diseaseModel.diseaseName,
            description = diseaseModel.description
        )
    }
    DiseaseRating(
        rating = diseaseModel.ranting,
        isDetailDescriptionValid = isDetailDescriptionValid
    )
    DiseaseSymptoms(
        symptoms = diseaseModel.symptoms
    )
    DiseaseType(
        type = diseaseModel.type,
        site = diseaseModel.site,
        reason = diseaseModel.reason
    )
    DiseaseDrugs(
        drugs = diseaseModel.drugs
    )
    DiseaseFirstAid(
        mild = diseaseModel.mild,
        severe = diseaseModel.severe
    )
    DiseasePreventive(
        preventives = contentToList(diseaseModel.preventive)
    )
    DiseaseCaution(
        cautions = contentToList(diseaseModel.caution)
    )
}

fun contentToList(content: String): List<String> {
    return content.split("\\n")
        .map { it.trim() }
        .filter { it.isNotEmpty() }
}

@Composable
fun DiseaseDetailDescription(
    diseaseName: String,
    description: String,
) {
    Spacer(modifier = Modifier.height(35.dp))

    DescriptionTitle(title = diseaseName)

    Text(
        text = description,
        color = Gray3,
        style = BaeBaeTypo.Body3,
        modifier = Modifier
            .padding(top = 10.dp, start = 23.dp, end = 23.dp)
    )

    DescriptionDivider()
}

@Composable
fun DiseaseRating(
    rating: Int = 4,
    isDetailDescriptionValid: Boolean = false,
) {
    if (!isDetailDescriptionValid) {
        Spacer(modifier = Modifier.height(30.dp))
    }

    DescriptionTitle(title = QuickDiseaseSymptomsRating)

    BoxWithConstraints(
        modifier = Modifier
            .padding(top = 13.dp, start = 23.dp, end = 23.dp)
    ) {
        Box(
            modifier = Modifier
                .width(maxWidth)
                .height(5.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(Gray1)
        )

        Box(
            modifier = Modifier
                .width(maxWidth * 0.25f)
                .height(5.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(Main2)
        )

        LazyRow(
            modifier = Modifier
                .padding(top = 6.dp)
                .fillMaxWidth(),
        ) {
            items(4) { index ->
                Text(
                    text = "4급",
                    color = if (index + rating == 4) Main2 else Gray3,
                    style = BaeBaeTypo.Caption2,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .width(maxWidth * 0.25f)
                )
            }
        }

        LazyRow(
            modifier = Modifier
                .padding(top = 18.dp)
                .fillMaxWidth(),
        ) {
            items(4) { index ->
                Text(
                    text = "감시.예방 중심",
                    color = if (index + rating == 4) Main2 else Color.Transparent,
                    style = BaeBaeTypo.Caption2,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .width(maxWidth * 0.25f)
                )
            }
        }
    }

    DescriptionDivider()
}

@Composable
fun DiseaseSymptoms(
    symptoms: List<SymptomItem>,
) {
    DescriptionTitle(title = QuickDiseaseSymptomsTitle)

    LazyRow(
        modifier = Modifier
            .padding(top = 10.dp),
        contentPadding = PaddingValues(horizontal = 23.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(symptoms) { symptom ->
            DiseaseSymptomItem(
                name = symptom.name
            )
        }
    }

    DescriptionDivider()
}

@Composable
fun DiseaseSymptomItem(
    name: String,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(Gray1)
    ) {
        Text(
            text = name,
            color = Gray3,
            style = BaeBaeTypo.Caption4,
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 12.dp)
        )
    }
}

@Composable
fun DiseaseType(
    type: String,
    site: String,
    reason: String,
) {
    DescriptionTitle(title = QuickDiseaseSymptomsCategory)

    Spacer(modifier = Modifier.height(15.dp))

    DescriptionSemiContent(
        title = QuickDiseaseSymptomsType,
        content = type,
    )

    Spacer(modifier = Modifier.height(15.dp))

    DescriptionSemiContent(
        title = QuickDiseaseSymptomsSite,
        content = site,
    )

    Spacer(modifier = Modifier.height(15.dp))

    DescriptionSemiContent(
        title = QuickDiseaseSymptomsReason,
        content = reason,
    )

    DescriptionDivider()
}

@Composable
fun DiseaseDrugs(
    drugs: List<DrugItem>,
) {
    DescriptionTitle(title = QuickDiseaseMedicine)

    LazyRow(
        modifier = Modifier
            .padding(top = 10.dp),
        contentPadding = PaddingValues(horizontal = 23.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(drugs) { drug ->
            DiseaseDrugsItem(
                name = drug.name,
                efficacy = drug.efficacy
            )
        }
    }

    DescriptionDivider()
}

@Composable
fun DiseaseDrugsItem(
    name: String,
    efficacy: String,
) {
    Row {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 100.dp, bottomStart = 100.dp))
                .background(Main2)
        ) {
            Text(
                text = name,
                color = White3,
                style = BaeBaeTypo.Caption4,
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 12.dp)
            )
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topEnd = 100.dp, bottomEnd = 100.dp))
                .background(Gray1)
        ) {
            Text(
                text = efficacy,
                color = Black1,
                style = BaeBaeTypo.Caption4,
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 12.dp)
            )
        }
    }
}

@Composable
fun DiseaseFirstAid(
    mild: String,
    severe: String,
) {
    DescriptionTitle(title = QuickDiseaseFirstAid)

    Spacer(modifier = Modifier.height(15.dp))

    DescriptionSemiContent(
        title = QuickDiseaseFirstAidMild,
        content = mild,
    )

    Spacer(modifier = Modifier.height(15.dp))

    DescriptionSemiContent(
        title = QuickDiseaseFirstAidSevere,
        content = severe,
    )

    DescriptionDivider()
}

@Composable
fun DiseasePreventive(
    preventives: List<String>,
) {
    DescriptionTitle(title = QuickDiseasePreventiveMeasure)

    DiseaseSemiContentList(
        contentList = preventives
    )

    DescriptionDivider()
}

@Composable
fun DiseaseCaution(
    cautions: List<String>,
) {
    DescriptionTitle(title = QuickDiseasePrecautions)

    DiseaseSemiContentList(
        contentList = cautions
    )

    Spacer(modifier = Modifier.height(50.dp))
}

@Composable
fun DescriptionTitle(
    title: String,
) {
    Text(
        text = title,
        color = Black1,
        style = BaeBaeTypo.Body1,
        modifier = Modifier
            .padding(start = 20.dp)
    )
}

@Composable
fun DescriptionSemiContent(
    title: String,
    content: String,
) {
    Box(
        modifier = Modifier
            .padding(start = 23.dp, end = 20.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            color = Black1,
            style = BaeBaeTypo.Body3,
            modifier = Modifier
        )

        Text(
            text = content,
            color = Gray3,
            style = BaeBaeTypo.Body3,
            modifier = Modifier
                .padding(start = 60.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun DiseaseSemiContentList(
    contentList: List<String>,
) {
    Column(
        modifier = Modifier
            .padding(top = 15.dp, start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        contentList.forEach { content ->
            DiseaseSemiContentListItem(
                content = content
            )
        }
    }
}

@Composable
fun DiseaseSemiContentListItem(
    content: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(Main2, shape = CircleShape)
        )

        Text(
            text = content,
            color = Gray3,
            style = BaeBaeTypo.Body3,
            modifier = Modifier
                .padding(start = 15.dp)
        )
    }
}

@Composable
fun DescriptionDivider() {
    Divider(
        modifier = Modifier
            .padding(20.dp)
            .border(2.dp, color = Gray1)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDiseaseDetail() {
    DiseaseDetailContent()
}