package com.paragon.usecases_composelistview.ui.components.search_filter

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

object SearchFilterListDestination{
    const val ROUTE ="search_filter_list_route"
}

fun NavGraphBuilder.searchFilterListGraph() {
    composable(
        route = SearchFilterListDestination.ROUTE
    ) {
        SearchFilterListScreen()
    }
}