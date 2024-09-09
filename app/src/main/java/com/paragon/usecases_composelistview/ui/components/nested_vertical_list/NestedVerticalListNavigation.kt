package com.paragon.usecases_composelistview.ui.components.nested_vertical_list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

object NestedVerticalListDestination{
    const val ROUTE ="nested_vertical_list_route"
}

fun NavGraphBuilder.nestedVerticalListGraph() {
    composable(
        route = NestedVerticalListDestination.ROUTE
    ) {
        NestedVerticalListScreen()
    }
}