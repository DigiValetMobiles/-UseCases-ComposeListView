package com.paragon.usecases_composelistview.ui.components.half_swipe

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.IntOffset
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import kotlin.math.roundToInt

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun SwipeableOptionItem(
    modifier: Modifier = Modifier,
    optionButton: @Composable () -> Unit,
    onDelete: () -> Unit = {},
    onOptionButtonRevealed: () -> Unit = { },
    onClickOptionButton: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val swipeState = rememberSwipeableState(SwipeState.INITIAL)
    val anchors = mapOf(
        0f to SwipeState.INITIAL,
        150f to SwipeState.OPTION_REVEALED,
        1000f to SwipeState.REMOVE_REVEALED,
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .swipeable(
                state = swipeState,
                anchors = anchors,
                thresholds = { _, to ->
                    if (to == SwipeState.OPTION_REVEALED || to == SwipeState.REMOVE_REVEALED)
                        FractionalThreshold(0.3f)
                    else FractionalThreshold(0.5f)
                },
                orientation = Orientation.Horizontal
            )
    ) {
        // Background content for the revealed options
        Box(
            Modifier
                .wrapContentSize()
                .clickable {
                    onClickOptionButton()
                }
        ) {
            if (swipeState.currentValue == SwipeState.OPTION_REVEALED || swipeState.currentValue == SwipeState.INITIAL)
                optionButton()
        }

        // Foreground content that is swipe able
        Box(
            Modifier.fillMaxSize()
                .offset { IntOffset(swipeState.offset.value.roundToInt(), 0) }) {
            content()
        }

        // Handle actions based on the swipe state
        LaunchedEffect(swipeState.currentValue) {
            when (swipeState.currentValue) {
                SwipeState.OPTION_REVEALED -> onOptionButtonRevealed()
                SwipeState.REMOVE_REVEALED -> {
                    onDelete()
                    swipeState.animateTo(SwipeState.INITIAL)
                }
                else -> { }
            }
        }
    }
}

enum class SwipeState {
    INITIAL,
    OPTION_REVEALED,
    REMOVE_REVEALED
}