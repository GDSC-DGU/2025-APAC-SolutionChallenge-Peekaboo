package com.peekaboo.ui.common.type

import com.peekaboo.design_system.R

enum class DiseaseType(
    val id: Int,
    val diseaseName: String,
    val diseaseNameEng: String,
    val diseaseImg: Int,
    val diseaseDetailImg: Int
) {
    Miliaria(1, "땀띠", "Miliaria", R.drawable.ic_disease_1, R.drawable.ic_disease_miliaria),
    Chickenpox(2, "수두", "Chickenpox", R.drawable.ic_disease_1, R.drawable.ic_disease_chickenpox),
    Roseola(3, "장미진", "Roseola", R.drawable.ic_disease_1, R.drawable.ic_disease_roseola),
    ContactDermatitis(4, "접촉성 피부염", "Contact Dermatitis", R.drawable.ic_disease_1, R.drawable.ic_disease_contact_dermatitis),
    Milia(5, "비립종", "Milia", R.drawable.ic_disease_1, R.drawable.ic_disease_milia),
    ErythemaToxicumNeonatorum(6, "중독성 홍반", "Erythema Toxicum Neonatorum", R.drawable.ic_disease_1, R.drawable.ic_disease_erythema_toxicum_neonatorum),
    AplasiaCutisCogenita(7, "선천성 피부 결손증", "Aplasia Cutis Cogenita", R.drawable.ic_disease_1, R.drawable.ic_disease_aplasia_cutis_congenita),
    Measles(8, "홍역", "Measles", R.drawable.ic_disease_1, R.drawable.ic_disease_measles),
    AtopicDermatitis(9, "아토피 피부염", "Atopic Dermatitis", R.drawable.ic_disease_1, R.drawable.ic_disease_atopic_dermatitis),
    SeborrheicDermatitis(10, "지루성 피부염", "Seborrheic Dermatitis", R.drawable.ic_disease_1, R.drawable.ic_disease_seborrheic_dermatitis),
    Impetigo(11, "농가진", "Impetigo", R.drawable.ic_disease_1, R.drawable.ic_disease_impetigo),
    Ringworm(12, "백선", "Ringworm", R.drawable.ic_disease_1, R.drawable.ic_disease_ringworm);

    companion object {
        fun getDiagnosisImg(constId: Int): Int =
            entries.firstOrNull { it.id == constId }?.diseaseDetailImg ?: R.drawable.ic_disease_1
    }
}