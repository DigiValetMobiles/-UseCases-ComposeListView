package com.paragon.usecases_composelistview.ui.components.bidirectional_swipe

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

object SwipeDestination{
    const val ROUTE ="swipe_route"
}

fun NavGraphBuilder.swipeGraph() {
    composable(
        route = SwipeDestination.ROUTE
    ) {
        SwipeScreen()
    }
}