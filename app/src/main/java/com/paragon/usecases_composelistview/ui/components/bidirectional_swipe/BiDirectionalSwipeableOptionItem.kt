package com.paragon.usecases_composelistview.ui.components.bidirectional_swipe

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import kotlin.math.roundToInt

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun BiDirectionalSwipeableOptions(
    modifier: Modifier = Modifier,
    startOptionButton: @Composable () -> Unit,
    onStartButtonRevealed: () -> Unit = { },
    onClickStartOptionButton: () -> Unit = {},
    endOptionButton: @Composable () -> Unit,
    onEndButtonRevealed: () -> Unit = { },
    onClickEndOptionButton: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val density = LocalDensity.current
    val defaultActionSize = 50.dp       // If need more wide button can update it as per need
    val actionSizePx = with(density) { defaultActionSize.toPx() }
    val endActionSizePx = with(density) { (defaultActionSize).toPx() }

    val swipeState = rememberSwipeableState(BiSwipeState.CENTER)
    val anchors = mapOf(
        -actionSizePx to BiSwipeState.START,
        0f to BiSwipeState.CENTER,
        endActionSizePx to BiSwipeState.END,
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .swipeable(
                state = swipeState,
                anchors = anchors,
                thresholds = { _, to ->
                    if (to == BiSwipeState.START || to == BiSwipeState.END)
                        FractionalThreshold(0.3f)
                    else FractionalThreshold(0.5f)
                },
                orientation = Orientation.Horizontal
            )
    ) {
        // Background content for the revealed options
        Box(Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterEnd)
                    .clickable { onClickEndOptionButton() }) {
                endOptionButton()
            }
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterStart)
                    .clickable { onClickStartOptionButton() }) {
                startOptionButton()
            }
        }

        // Foreground content that is swipe able
        Box(
            Modifier
                .fillMaxSize()
                .offset { IntOffset(swipeState.offset.value.roundToInt(), 0) }) {
            content()
        }

        // Handle actions based on the swipe state
        LaunchedEffect(swipeState.currentValue) {
            when (swipeState.currentValue) {
                BiSwipeState.START -> onStartButtonRevealed()
                BiSwipeState.END -> onEndButtonRevealed()
                else -> { }
            }
        }
    }
}

enum class BiSwipeState {
    START,
    CENTER,
    END
}