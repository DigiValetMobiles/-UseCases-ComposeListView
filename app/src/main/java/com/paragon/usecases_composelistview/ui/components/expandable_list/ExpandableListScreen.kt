package com.paragon.usecases_composelistview.ui.components.expandable_list

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.paragon.usecases_composelistview.R

@Composable
fun ExpandableListScreen() {
    val viewModel = ExpandableListViewModel()
    val categoryItemList by viewModel.categoryItemList.observeAsState()

    LazyColumn(
        Modifier
            .safeContentPadding()
            .fillMaxWidth()
    ) {

        item {
            Text(
                text = "Expandable list",
                color = Color.White,
                fontSize = TextUnit(21f, TextUnitType.Sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(10.dp)
            )
        }

        categoryItemList?.forEach { (categoryItem, categoryChildItems) ->
            val isExpanded = mutableStateOf(categoryItem.isExpanded)
            val totalCount = mutableIntStateOf(categoryItem.totalCount)
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .clickable {
                            isExpanded.value = isExpanded.value.not()
                            viewModel.onCategoryExpand(categoryItem)
                        }
                        .padding(10.dp)
                ) {
                    Text(
                        text = "${categoryItem.name} (${totalCount.intValue})",
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                    Icon(
                        painter = painterResource(id = if (isExpanded.value) R.drawable.ic_expanded else R.drawable.ic_collapsed),
                        contentDescription = "",
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }
            }
            if (isExpanded.value) {
                items(categoryChildItems.size) {
                    val itemCount = remember { mutableIntStateOf(categoryChildItems[it].totalCount) }
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = categoryChildItems[it].name,
                                color = Color.Black,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                                    .align(Alignment.CenterStart)
                            )
                            Row(Modifier.align(Alignment.CenterEnd), verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_subtract),
                                    contentDescription = "",
                                    modifier = Modifier.clickable {
                                        if (categoryChildItems[it].totalCount > 0) {
                                            itemCount.intValue -= 1
                                            totalCount.intValue -= 1
                                            viewModel.onCategoryItemCountUpdated(
                                                categoryItem,
                                                categoryChildItems[it],
                                                (categoryChildItems[it].totalCount - 1)
                                            )
                                        }
                                    }
                                )
                                Text(
                                    text = itemCount.intValue.toString(),
                                    color = Color.Black,
                                    modifier = Modifier.padding(10.dp)
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_add),
                                    contentDescription = "",
                                    modifier = Modifier.clickable {
                                        itemCount.intValue += 1
                                        totalCount.intValue += 1
                                        viewModel.onCategoryItemCountUpdated(categoryItem, categoryChildItems[it], (categoryChildItems[it].totalCount + 1))
                                    }
                                )
                            }
                        }
                        Spacer(
                            Modifier
                                .background(Color.LightGray)
                                .fillMaxWidth()
                                .height(0.5.dp)
                        )
                    }
                }
            }
        }
    }
}

enum class ListItemType {
    PARENT,
    CHILD
}

data class ListItem(
    val type: ListItemType,
    val name: String,
    var totalCount: Int = 0,
    var isExpanded: Boolean = false
)