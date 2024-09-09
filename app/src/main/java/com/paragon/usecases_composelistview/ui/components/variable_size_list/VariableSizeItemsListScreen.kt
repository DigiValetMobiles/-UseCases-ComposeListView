package com.paragon.usecases_composelistview.ui.components.variable_size_list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.paragon.usecases_composelistview.ImagesList

@Composable
fun VariableSizeItemsListScreen() {
    val list = remember { ImagesList }
    
    LazyVerticalStaggeredGrid(
        modifier = Modifier.safeContentPadding(),
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        content = {
            items(
                count = list.size,
                key = {
                    list[it]+it
                },
                itemContent = {
                    AsyncImage(
                        model = list[it],
                        contentDescription = list[it],
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(end = 4.dp)
                    )
                }
            )
        }
    )
}