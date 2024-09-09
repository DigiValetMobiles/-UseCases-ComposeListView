package com.paragon.usecases_composelistview.ui.components.changeable_list_view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.paragon.usecases_composelistview.ImagesList

@Composable
fun ChangeAbleListViewScreen() {

    val list = remember { ImagesList }
    var listType by remember { mutableStateOf(ListType.VERTICAL) }

    Column(
        modifier = Modifier.safeContentPadding(),
    ) {
        Row {
            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = listType == ListType.VERTICAL,
                    onCheckedChange = {
                        listType = ListType.VERTICAL
                    }
                )
                Text(text = "Vertical")
            }
            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = listType == ListType.GRID,
                    onCheckedChange = {
                        listType = ListType.GRID
                    }
                )
                Text(text = "Grid")
            }
            Row(modifier = Modifier.weight(1.2f), verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = listType == ListType.STAGGERED,
                    onCheckedChange = {
                        listType = ListType.STAGGERED
                    }
                )
                Text(text = "Staggered")
            }
        }
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(if (listType == ListType.VERTICAL) 1 else 2),
            verticalItemSpacing = 1.dp,
            content = {
                items(
                    count = list.size,
                    key = {
                        list[it]+it
                    },
                    itemContent = {
                        when(listType) {
                            ListType.VERTICAL, ListType.GRID -> {
                                AsyncImage(
                                    model = list[it],
                                    contentDescription = list[it],
                                    contentScale = ContentScale.FillWidth,
                                    modifier = Modifier
                                        .height(if (listType == ListType.VERTICAL) 200.dp else 100.dp)
                                        .padding(end = 1.dp)
                                )
                            }
                            ListType.STAGGERED -> {
                                AsyncImage(
                                    model = list[it],
                                    contentDescription = list[it],
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                        .padding(end = 1.dp)
                                )
                            }
                        }

                    }
                )
            }
        )
    }
}

enum class ListType {
    VERTICAL,
    GRID,
    STAGGERED
}