package com.paragon.usecases_composelistview.ui.components.drag_drop_list_item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.paragon.usecases_composelistview.ui.components.drag_drop_list_item.util.ItemPosition

class DragDropItemViewModel {
    var items by mutableStateOf(List(500) {
        "Drag Me$it"
    })


    fun moveItem(from: ItemPosition, to: ItemPosition) {
        items = items.toMutableList().apply {
            add(to.index, removeAt(from.index))
        }
    }
}