package com.paragon.usecases_composelistview.ui.components.view_types_list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

object ViewTypesListDestination{
    const val ROUTE ="view_types_list_route"
}

fun NavGraphBuilder.viewTypesListGraph() {
    composable(
        route = ViewTypesListDestination.ROUTE
    ) {
        ViewTypesListScreen()
    }
}