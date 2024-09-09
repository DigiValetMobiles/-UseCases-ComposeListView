package com.paragon.usecases_composelistview.ui.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp


@Composable
fun HomeScreen(
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
    val options = listOf(
        "Half Swipe to reveal & Full to delete.",
        "Swipe LTR and RTL.",
        "Multiple Items state management.",
        "Sticky item View with actionable.",
        "Sync tabs with item position on scroll.",
        "Search and Filter on List View",
        "Drag Drop List View item",
        "Multiple Gesture on List View item",
        "List View with variable size items",
        "Changeable List View type",
        "List with edittext",
        "Nested Lists",
        "Nested Vertical Lists"
    )
    Scaffold(modifier = Modifier.safeContentPadding()) { innerPadding->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Black)
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                text = "Compose List View POCS",
                color = Color.White,
                fontSize = TextUnit(26f, TextUnitType.Sp),
            )
            LazyColumn(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 5.dp, start = 15.dp, end = 15.dp)
            ) {
                items(options.size) {
                    Text(
                        text = options[it],
                        color = Color.Gray,
                        fontSize = TextUnit(16f, TextUnitType.Sp),
                        modifier = Modifier.fillMaxWidth()
                            .clickable {
                            when(it) {
                                0 -> navigateToHalfSwipeOptScreen()
                                1 -> navigateToSwipeOptScreen()
                                2 -> navigateToLargeListStateOptScreen()
                                3 -> navigateToStickyActionableList()
                                4 -> navigateToStickyActionableList()
                                5 -> navigateToSearchFilterList()
                                6 -> navigateToDragDropItem()
                                7 -> navigateToMultipleGestureList()
                                8 -> navigateToVariableSizeItemsList()
                                9 -> navigateToChangeAbleListView()
                                10 -> navigateToEditTextList()
                                11 -> navigateToNestedVHLists()
                                12 -> navigateToNestedVerticalList()
                            }
                        }.padding(top = 10.dp)
                    )
                    Divider(thickness = 1.dp, color = Color.Gray)
                }
            }
        }
    }
}