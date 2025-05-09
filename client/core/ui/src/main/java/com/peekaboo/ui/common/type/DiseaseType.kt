package com.peekaboo.ui.common.type

import com.peekaboo.design_system.R

enum class DiseaseType(
    val id: Int,
    val diseaseName: String,
    val diseaseNameEng: String,
    val diseaseImg: Int,
) {
    Miliaria(1, "땀띠", "Miliaria", R.drawable.ic_disease_1),
    Chickenpox(2, "수두", "Chickenpox", R.drawable.ic_disease_1),
    Roseola(3, "장미진", "Roseola", R.drawable.ic_disease_1),
    ContactDermatitis(4, "접촉성 피부염", "Contact Dermatitis", R.drawable.ic_disease_1),
    Milia(5, "비립종", "Milia", R.drawable.ic_disease_1),
    ErythemaToxicumNeonatorum(6, "중독성 홍반", "Erythema Toxicum Neonatorum", R.drawable.ic_disease_1),
    AplasiaCutisCogenita(7, "선천성 피부 결손증", "Aplasia Cutis Cogenita", R.drawable.ic_disease_1),
    Measles(8, "홍역", "Measles", R.drawable.ic_disease_1),
    AtopicDermatitis(9, "아토피 피부염", "Atopic Dermatitis", R.drawable.ic_disease_1),
    SeborrheicDermatitis(10, "지루성 피부염", "Seborrheic Dermatitis", R.drawable.ic_disease_1),
    Impetigo(11, "농가진", "Impetigo", R.drawable.ic_disease_1),
    Ringworm(12, "백선", "Ringworm", R.drawable.ic_disease_1);
}