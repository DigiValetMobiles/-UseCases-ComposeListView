package com.paragon.usecases_composelistview.ui.components.sticky_actionable_item_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import kotlinx.coroutines.launch

@Composable
fun StickyActionableListScreen() {
    val verticalListState = rememberLazyListState()
    val horizontalListState = rememberLazyListState()
    val verticalVisibleItemIndex by remember { derivedStateOf { verticalListState.firstVisibleItemIndex } }
    val horizontalVisibleItemIndex by remember { derivedStateOf { horizontalListState.firstVisibleItemIndex } }
    val coroutineScope = rememberCoroutineScope()

    val items  = mapOf(
        Category("Vegetables", remember { mutableStateOf(true) }) to listOf(
            CategoryItem("Peas", Category("Vegetables")),
            CategoryItem("Onion", Category("Vegetables")),
            CategoryItem("Spinach", Category("Vegetables"))
        ),
        Category("Fruits") to listOf(
            CategoryItem("Apple", Category("Fruits")),
            CategoryItem("Banana", Category("Fruits")),
            CategoryItem("Orange", Category("Fruits")),
            CategoryItem("Strawberry", Category("Fruits")),
            CategoryItem("Berry", Category("Fruits"))
        ),
        Category("Gym") to listOf(
            CategoryItem("Popeye", Category("Gym")),
            CategoryItem("Fitness zone", Category("Gym")),
            CategoryItem("Core Fitness", Category("Gym")),
            CategoryItem("Fit Tribe", Category("Gym")),
            CategoryItem("Exercise", Category("Gym"))
        ),
        Category("Pet") to listOf(
            CategoryItem("Happy Tails", Category("Pet")),
            CategoryItem("Pawfectly", Category("Pet")),
            CategoryItem("Grooming Dales", Category("Pet")),
            CategoryItem("Beyond Pets", Category("Pet")),
        ),
        Category("Swimming Pool") to listOf(
            CategoryItem("Plunge Pool", Category("Gym")),
            CategoryItem("Aqua Oasis", Category("Gym")),
            CategoryItem("Above Ground", Category("Gym")),
            CategoryItem("Infinity pools", Category("Gym")),
        ),
        Category("Spa") to listOf(
            CategoryItem("EveryDay Spa", Category("Spa")),
            CategoryItem("Avalon Spa", Category("Spa")),
            CategoryItem("Blissful Moment ", Category("Spa")),
            CategoryItem("Elegance Spa", Category("Spa")),
        ),
        Category("Club House") to listOf(
            CategoryItem("Beehive", Category("Club House")),
            CategoryItem("Havana Club", Category("Club House")),
            CategoryItem("The Book Club", Category("Club House")),
            CategoryItem("Conga Room", Category("Club House")),
        )
    )

    if ((verticalVisibleItemIndex in items.getCountUpToIndex(horizontalVisibleItemIndex)..<items.getCountUpToIndex(horizontalVisibleItemIndex + 1)).not()) {
        coroutineScope.launch {
            if (verticalVisibleItemIndex < items.getCountUpToIndex(horizontalVisibleItemIndex))
                horizontalListState.animateScrollToItem( horizontalVisibleItemIndex - 1)
            else if (verticalVisibleItemIndex > items.getCountUpToIndex(horizontalVisibleItemIndex + 1))
                horizontalListState.animateScrollToItem( horizontalVisibleItemIndex + 1)
        }
    }

    Column(modifier = Modifier.safeContentPadding()) {
        LazyRow(state = horizontalListState) {
            items(
                count = items.keys.size,
                key = {
                    items.keys.toList()[it].name
                },
                itemContent = {
                    Text(
                        text = items.keys.toList()[it].name,
                        color = if (verticalVisibleItemIndex in items.getCountUpToIndex(it)..<items.getCountUpToIndex(it + 1)) Color.White else Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(start = 10.dp)
                            .background(
                                if (verticalVisibleItemIndex in items.getCountUpToIndex(it)..<items.getCountUpToIndex(
                                        it + 1
                                    )
                                ) Color.Blue else Color.White
                            )
                            .padding(vertical = 10.dp, horizontal = 10.dp)
                            .clickable {
                                items.keys
                                    .toList()
                                    .forEach { it.isSelected.value = false }
                                items.keys.toList()[it].isSelected.value =
                                    items.keys.toList()[it].isSelected.value.not()
                                coroutineScope.launch {
                                    var count = 0
                                    items.entries.forEachIndexed { index, entry ->
                                        if (index < it)
                                            count += (entry.value.size + 1)
                                    }
                                    verticalListState.animateScrollToItem(count)
                                }
                            }
                    )
                }
            )
        }

        LazyColumn(state = verticalListState) {
            items.entries.forEach { entry->
                // we can use stickyHeader to build header item, if we want to stick the header the header in top if not we can use item
                item {                      // Header item is shown through item to let the item scroll
                    Text(
                        text = entry.key.name,
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp)
                            .background(Color.LightGray)
                            .padding(vertical = 10.dp, horizontal = 10.dp)
                    )
                }
                items(
                    count = entry.value.size,
                    key = {
                        entry.value[it].name
                    },
                    itemContent = {
                        Text(
                            text = entry.value[it].name,
                            color = Color.Gray,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(start = 10.dp)
                                .padding(vertical = 20.dp, horizontal = 10.dp)
                        )
                        Divider(thickness = .5.dp, color = Color.LightGray)
                    }
                )
            }
        }
    }
}

fun Map<Category, List<CategoryItem>>.getCountUpToIndex(indexUpTo: Int): Int {
    var count = 0
    entries.forEachIndexed { index, entry ->
        if (index < indexUpTo)
            count += (entry.value.size +1)
    }
    return  count
}

data class Category(
    val name: String,
    val isSelected: MutableState<Boolean> = mutableStateOf(false)
)

data class CategoryItem(
    val name: String,
    val category: Category,
    var isSelected: MutableState<Boolean> = mutableStateOf(false)
)
