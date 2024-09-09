package com.paragon.usecases_composelistview.ui.components.variable_size_list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

object VariableSizeItemsListDestination{
    const val ROUTE ="variable_size_items_list_route"
}

fun NavGraphBuilder.variableSizeItemsListGraph() {
    composable(
        route = VariableSizeItemsListDestination.ROUTE
    ) {
        VariableSizeItemsListScreen()
    }
}