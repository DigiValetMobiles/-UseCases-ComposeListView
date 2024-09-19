package com.paragon.usecases_composelistview.ui.components.drag_drop_list_item

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import com.paragon.usecases_composelistview.ui.components.drag_drop_list_item.util.ReorderableItem
import com.paragon.usecases_composelistview.ui.components.drag_drop_list_item.util.detectReorderAfterLongPress
import com.paragon.usecases_composelistview.ui.components.drag_drop_list_item.util.rememberReorderableLazyListState
import com.paragon.usecases_composelistview.ui.components.drag_drop_list_item.util.reorderable

@Composable
fun DragDropItemScreen() {
    val viewModel = DragDropItemViewModel()
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
        val rowState = rememberReorderableLazyListState(onMove = viewModel::moveItem)

        LazyRow(
            state = rowState.listState,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(top = 10.dp)
                .then(Modifier.reorderable(rowState))
                .detectReorderAfterLongPress(rowState),
        ) {
            items(viewModel.items, { item -> item }) { item ->
                ReorderableItem(rowState, item) { dragging ->
                    val scale = animateFloatAsState(if (dragging) 1.1f else 1.0f, label = "")
                    val elevation = if (dragging) 8.dp else 0.dp
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(100.dp)
                            .scale(scale.value)
                            .shadow(elevation, RoundedCornerShape(24.dp))
                            .clip(RoundedCornerShape(24.dp))
                            .background(Color.Black)
                    ) {
                        Text(item)
                    }
                }
            }
        }

        val state = rememberReorderableLazyListState(onMove = viewModel::moveItem)
                // Define a list state reference with onMove method call back listeners
        LazyColumn(
            state = state.listState,                                // Assign the declared state with lazy list
            modifier = Modifier.padding(top = 10.dp)
                .reorderable(state)) {               // Add reorder able modifier with list
            items(viewModel.items, { it },
                itemContent = { item ->
                    ReorderableItem(                                // Define list item as reorder able item child
                        state = state,                              // Add state with reorder able item
                        key = item
                    ) {
                        val elevation = animateDpAsState(if (it) 8.dp else 0.dp, label = "")
                                                                    // Define an object to show elevation of item on drag
                        Column(Modifier
                            .detectReorderAfterLongPress(state)     // Add long press listener modifier with item
                            .shadow(elevation.value)                // Use elevation as shadow with item
                        ) {
                            Text(
                                text = item,
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
                }
            )
        }
    }
}