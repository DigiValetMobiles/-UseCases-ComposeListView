package com.paragon.usecases_composelistview.ui.components.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

object HomeDestination{
    const val ROUTE ="home_route"
}

fun NavGraphBuilder.homePageGraph(
    navigateToHalfSwipeOptScreen: () -> Unit,
    navigateToSwipeOptScreen: () -> Unit,
    navigateToLargeListStateOptScreen: () -> Unit,
    navigateToStickyActionableList: () -> Unit,
    navigateToSearchFilterList: () -> Unit,
    navigateToDragDropItem: () -> Unit,
    navigateToMultipleGestureList: () -> Unit,
    navigateToVariableSizeItemsList: () -> Unit,
    navigateToChangeAbleListView: () -> Unit,
    navigateToEditTextList: () -> Unit,
    navigateToNestedVerticalList: () -> Unit,
    navigateToNestedVHLists: () -> Unit,
) {
    composable(
        route = HomeDestination.ROUTE
    ) {
        HomeScreen(
            navigateToHalfSwipeOptScreen,
            navigateToSwipeOptScreen,
            navigateToLargeListStateOptScreen,
            navigateToStickyActionableList,
            navigateToSearchFilterList,
            navigateToDragDropItem,
            navigateToMultipleGestureList,
            navigateToVariableSizeItemsList,
            navigateToChangeAbleListView,
            navigateToEditTextList,
            navigateToNestedVerticalList,
            navigateToNestedVHLists
        )
    }
}