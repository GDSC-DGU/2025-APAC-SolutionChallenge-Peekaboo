package com.peekaboo.ui.common.content

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import com.peekaboo.design_system.GrayShimmer1
import com.peekaboo.design_system.GrayShimmer2
import com.peekaboo.design_system.MainShimmer1
import com.peekaboo.design_system.MainShimmer2

@Composable
fun LoadingShimmerEffect(
    content: @Composable (Brush) -> Unit,
) {
    val shimmerColors = listOf(
        GrayShimmer2, GrayShimmer1, GrayShimmer2
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    content(brush)
}

@Composable
fun LoadingShimmerMainEffect(
    content: @Composable (Brush) -> Unit,
) {
    val shimmerColors = listOf(
        MainShimmer2, MainShimmer1, MainShimmer2
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    content(brush)
}