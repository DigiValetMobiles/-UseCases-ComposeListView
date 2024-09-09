package com.paragon.usecases_composelistview.ui.components.edittext_list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

object EditTextListDestination{
    const val ROUTE ="edittext_list_route"
}

fun NavGraphBuilder.editTextListGraph() {
    composable(
        route = EditTextListDestination.ROUTE
    ) {
        EditTextListScreen()
    }
}