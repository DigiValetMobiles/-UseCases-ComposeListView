package com.paragon.usecases_composelistview.ui.components.changeable_list_view

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

object ChangeAbleListViewDestination{
    const val ROUTE ="change_able_list_view_route"
}

fun NavGraphBuilder.changeAbleListViewGraph() {
    composable(
        route = ChangeAbleListViewDestination.ROUTE
    ) {
        ChangeAbleListViewScreen()
    }
}