package com.paragon.usecases_composelistview.ui.navigator

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.paragon.usecases_composelistview.ui.components.half_swipe.HalfSwipeDestination
import com.paragon.usecases_composelistview.ui.components.half_swipe.halfSwipeGraph
import com.paragon.usecases_composelistview.ui.components.home.homePageGraph
import com.paragon.usecases_composelistview.ui.components.bidirectional_swipe.SwipeDestination
import com.paragon.usecases_composelistview.ui.components.bidirectional_swipe.swipeGraph
import com.paragon.usecases_composelistview.ui.components.changeable_list_view.ChangeAbleListViewDestination
import com.paragon.usecases_composelistview.ui.components.changeable_list_view.changeAbleListViewGraph
import com.paragon.usecases_composelistview.ui.components.drag_drop_list_item.DragDropItemDestination
import com.paragon.usecases_composelistview.ui.components.drag_drop_list_item.dragDropItemGraph
import com.paragon.usecases_composelistview.ui.components.edittext_list.EditTextListDestination
import com.paragon.usecases_composelistview.ui.components.edittext_list.editTextListGraph
import com.paragon.usecases_composelistview.ui.components.large_list_state.LargeListStateDestination
import com.paragon.usecases_composelistview.ui.components.large_list_state.largeListStateGraph
import com.paragon.usecases_composelistview.ui.components.multiple_gesture.MultipleGestureListDestination
import com.paragon.usecases_composelistview.ui.components.multiple_gesture.multipleGestureListGraph
import com.paragon.usecases_composelistview.ui.components.nested_vertical_list.NestedVerticalListDestination
import com.paragon.usecases_composelistview.ui.components.nested_vertical_list.nestedVerticalListGraph
import com.paragon.usecases_composelistview.ui.components.nested_vh_list.NestedVHListDestination
import com.paragon.usecases_composelistview.ui.components.nested_vh_list.nestedVHListGraph
import com.paragon.usecases_composelistview.ui.components.search_filter.SearchFilterListDestination
import com.paragon.usecases_composelistview.ui.components.search_filter.searchFilterListGraph
import com.paragon.usecases_composelistview.ui.components.sticky_actionable_item_list.StickyActionableListDestination
import com.paragon.usecases_composelistview.ui.components.sticky_actionable_item_list.stickyActionableListGraph
import com.paragon.usecases_composelistview.ui.components.variable_size_list.VariableSizeItemsListDestination
import com.paragon.usecases_composelistview.ui.components.variable_size_list.variableSizeItemsListGraph

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier, navController: NavHostController, startDestination: String,
) {
    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = startDestination,
        enterTransition = { fadeIn(animationSpec = tween(1)) },
        exitTransition = { fadeOut(animationSpec = tween(1)) },
    ) {
        homePageGraph(
            navigateToHalfSwipeOptScreen = {
                navController.navigate(HalfSwipeDestination.ROUTE)
            },
            navigateToSwipeOptScreen = {
                navController.navigate(SwipeDestination.ROUTE)
            },
            navigateToLargeListStateOptScreen = {
                navController.navigate(LargeListStateDestination.ROUTE)
            },
            navigateToStickyActionableList = {
                navController.navigate(StickyActionableListDestination.ROUTE)
            },
            navigateToSearchFilterList = {
                navController.navigate(SearchFilterListDestination.ROUTE)
            },
            navigateToDragDropItem = {
                navController.navigate(DragDropItemDestination.ROUTE)
            },
            navigateToMultipleGestureList = {
                navController.navigate(MultipleGestureListDestination.ROUTE)
            },
            navigateToVariableSizeItemsList = {
                navController.navigate(VariableSizeItemsListDestination.ROUTE)
            },
            navigateToChangeAbleListView = {
                navController.navigate(ChangeAbleListViewDestination.ROUTE)
            },
            navigateToEditTextList = {
                navController.navigate(EditTextListDestination.ROUTE)
            },
            navigateToNestedVerticalList = {
                navController.navigate(NestedVerticalListDestination.ROUTE)
            },
            navigateToNestedVHLists = {
                navController.navigate(NestedVHListDestination.ROUTE)
            }
        )

        halfSwipeGraph()

        swipeGraph()

        largeListStateGraph()

        stickyActionableListGraph()

        searchFilterListGraph()

        multipleGestureListGraph()

        dragDropItemGraph()

        variableSizeItemsListGraph()

        changeAbleListViewGraph()

        editTextListGraph()

        nestedVerticalListGraph()

        nestedVHListGraph()
    }
}