package com.peekaboo.diagnosis.type

import com.peekaboo.design_system.R

enum class SelectAreaType(
    val position: String,
    val bodyImg: Int
) {
    HEAD("head", R.drawable.ic_body_head),
    FACE("face", R.drawable.ic_body_face),
    STOMACH("stomach", R.drawable.ic_body_stomach),
    LEGLEFT("leg", R.drawable.ic_body_leg_left),
    LEGRIGHT("leg", R.drawable.ic_body_leg_right),
    ARMLEFT("arm", R.drawable.ic_body_arm_left),
    ARMRIGHT("arm", R.drawable.ic_body_arm_right),

    BACK("back", R.drawable.ic_body_back),
    HIP("hip", R.drawable.ic_body_hip),
    HANDLEFT("hand", R.drawable.ic_body_hand_left),
    HANDRIGHT("hand", R.drawable.ic_body_hand_right),
    FOOTLEFT("foot", R.drawable.ic_body_foot_left),
    FOOTRIGHT("foot", R.drawable.ic_body_foot_right);
}