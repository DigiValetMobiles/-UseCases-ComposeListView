package com.paragon.usecases_composelistview.ui.components.half_swipe

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

object HalfSwipeDestination{
    const val ROUTE ="half_swipe_route"
}

fun NavGraphBuilder.halfSwipeGraph() {
    composable(
        route = HalfSwipeDestination.ROUTE
    ) {
        HalfSwipeScreen()
    }
}