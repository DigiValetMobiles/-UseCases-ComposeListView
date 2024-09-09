package com.paragon.usecases_composelistview.ui.components.search_filter

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.paragon.usecases_composelistview.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchFilterListScreen() {
    var sortOrder by remember { mutableIntStateOf(0) }
    var searchQuery by remember { mutableStateOf("") }
    val itemsList = mutableListOf<String>()
    for (i in 1..100) {
        itemsList.add("Item $i")
    }
    val filteredItemsList = remember(searchQuery) {
        itemsList.filter { it.contains(searchQuery, ignoreCase = true) }
    }

    LazyColumn(Modifier.safeContentPadding()) {
        stickyHeader {
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextField(
                    value = searchQuery,
                    onValueChange = { query ->
                        searchQuery = query
                    },
                    label = { Text("Search") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        items(filteredItemsList.size) { item ->
            Text(
                text = filteredItemsList[item],
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            Divider()
        }
    }
}
