package com.paragon.usecases_composelistview.ui.components.expandable_list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

object ExpandableListDestination{
    const val ROUTE ="expandable_list_route"
}

fun NavGraphBuilder.expandableListGraph() {
    composable(
        route = ExpandableListDestination.ROUTE
    ) {
        ExpandableListScreen()
    }
}