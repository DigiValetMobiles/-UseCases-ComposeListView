package com.paragon.usecases_composelistview.ui.components.multiple_gesture

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

object MultipleGestureListDestination{
    const val ROUTE ="multiple_gesture_list_route"
}

fun NavGraphBuilder.multipleGestureListGraph() {
    composable(
        route = MultipleGestureListDestination.ROUTE
    ) {
        MultipleGestureListScreen()
    }
}