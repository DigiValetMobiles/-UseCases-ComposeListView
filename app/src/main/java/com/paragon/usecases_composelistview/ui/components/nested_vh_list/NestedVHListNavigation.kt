package com.paragon.usecases_composelistview.ui.components.nested_vh_list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

object NestedVHListDestination{
    const val ROUTE ="nested_vh_list_route"
}

fun NavGraphBuilder.nestedVHListGraph() {
    composable(
        route = NestedVHListDestination.ROUTE
    ) {
        NestedVHScreen()
    }
}