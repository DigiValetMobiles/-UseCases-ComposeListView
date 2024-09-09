package com.paragon.usecases_composelistview.ui.components.drag_drop_list_item

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

object DragDropItemDestination{
    const val ROUTE ="drag_drop_item_route"
}

fun NavGraphBuilder.dragDropItemGraph() {
    composable(
        route = DragDropItemDestination.ROUTE
    ) {
        DragDropItemScreen()
    }
}