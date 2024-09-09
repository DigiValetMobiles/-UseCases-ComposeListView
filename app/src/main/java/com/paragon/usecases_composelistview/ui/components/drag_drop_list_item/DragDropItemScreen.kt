package com.paragon.usecases_composelistview.ui.components.drag_drop_list_item

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun DragDropItemScreen() {
    val coroutineScope = rememberCoroutineScope()
    var list by remember {
        mutableStateOf(
            listOf("Drag Me1", "Drag Me2", "Drag Me3", "Drag Me4", "Drag Me5")
        )
    }

    // Keeps track of which item is currently being dragged
    var draggedItemIndex by remember { mutableStateOf<Int?>(null) }

    // Offset state to track the position of the dragged item
    var dragOffset by remember { mutableStateOf(Offset.Zero) }

    Column(
        modifier = Modifier.safeContentPadding()
    ) {
        androidx.compose.material3.Text(
            text = "Drag/Drop below items",
            color = Color.White,
            fontSize = TextUnit(21f, TextUnitType.Sp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(10.dp)
        )
        LazyColumn() {
            items(
                count = list.size ?: 0,
                key = {
                    it
                },
                itemContent = { index ->
                    Column(
                        modifier = Modifier
                            .offset {
                                // If the current item is being dragged, offset it by the drag amount
                                if (draggedItemIndex == index) {
                                    IntOffset(dragOffset.x.roundToInt(), dragOffset.y.roundToInt())
                                } else {
                                    IntOffset.Zero
                                }
                            }
                            .pointerInput(Unit) {
                                detectDragGestures(
                                    onDragStart = {
                                        // Record which item is being dragged
                                        draggedItemIndex = index
                                    },
                                    onDrag = { change, dragAmount ->
                                        change.consume() // Consume the drag event
                                        coroutineScope.launch {
                                            // Update drag offset as the item is dragged
                                            dragOffset += dragAmount

                                            // Calculate the position of the item under the dragged item
                                            val currentDraggedItemOffset =
                                                dragOffset.y + (index * 50.dp.toPx())
                                            val targetIndex =
                                                (currentDraggedItemOffset / 50.dp.toPx())
                                                    .toInt()
                                                    .coerceIn(0, list.size - 1)

                                            // If the item crosses into another item's space, reorder them
                                            if (targetIndex != index) {
                                                list = list
                                                    .toMutableList()
                                                    .apply {
                                                        add(targetIndex, removeAt(index))
                                                    }
                                                draggedItemIndex = targetIndex
                                            }
                                        }
                                    },
                                    onDragEnd = {
                                        // Reset the dragged item and offset when the drag ends
                                        draggedItemIndex = null
                                        dragOffset = Offset.Zero
                                    }
                                )
                            }
                    ) {
                        Text(
                            text = list[index],
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(10.dp)
                                .padding(top = 20.dp)
                        )
                        Divider(thickness = 1.dp, color = Color.Gray)
                    }
                }
            )
        }
    }
}