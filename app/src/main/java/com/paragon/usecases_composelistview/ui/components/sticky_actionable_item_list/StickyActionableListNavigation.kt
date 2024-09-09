package com.paragon.usecases_composelistview.ui.components.sticky_actionable_item_list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

object StickyActionableListDestination{
    const val ROUTE ="sticky_actionable_list_route"
}

fun NavGraphBuilder.stickyActionableListGraph() {
    composable(
        route = StickyActionableListDestination.ROUTE
    ) {
        StickyActionableListScreen()
    }
}