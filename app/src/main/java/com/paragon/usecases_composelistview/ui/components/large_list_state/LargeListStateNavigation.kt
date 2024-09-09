package com.paragon.usecases_composelistview.ui.components.large_list_state

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

object LargeListStateDestination{
    const val ROUTE ="large_list_state_route"
}

fun NavGraphBuilder.largeListStateGraph() {
    composable(
        route = LargeListStateDestination.ROUTE
    ) {
        LargeListStateScreen()
    }
}