package com.peekaboo.ui.common.type

import com.peekaboo.design_system.R

enum class DiseaseType(
    val id: Int,
    val diseaseName: String,
    val diseaseNameEng: String,
    val diseaseImg: Int,
) {
    AtopicDermatitis(1, "아토피 피부염", "Atopic Dermatitis", R.drawable.ic_disease_1),
    SeborrheicDermatitis(2, "지루성 피부염", "Seborrheic Dermatitis", R.drawable.ic_disease_1),
    Impetigo(3, "농가진", "Impetigo", R.drawable.ic_disease_1),
    Ringworm(4, "백선", "Ringworm", R.drawable.ic_disease_1),
    Chickenpox(5, "수두", "Chickenpox", R.drawable.ic_disease_1),
    Roseola(6, "장미진", "Roseola", R.drawable.ic_disease_1),
    ContactDermatitis(7, "접촉성 피부염", "Contact Dermatitis", R.drawable.ic_disease_1),
    Miliaria(8, "땀띠", "Miliaria", R.drawable.ic_disease_1),
    Milia(9, "비립종", "Milia", R.drawable.ic_disease_1),
    ErythemaToxicumNeonatorum(10, "중독성 홍반", "Erythema Toxicum Neonatorum", R.drawable.ic_disease_1),
    AplasiaCutisCogenita(11, "선천성 피부 결손증", "Aplasia Cutis Cogenita", R.drawable.ic_disease_1),
    Measles(12, "홍역", "Measles", R.drawable.ic_disease_1);
}