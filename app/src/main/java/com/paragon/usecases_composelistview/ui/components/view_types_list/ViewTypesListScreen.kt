package com.paragon.usecases_composelistview.ui.components.view_types_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.paragon.usecases_composelistview.ImagesList
import com.paragon.usecases_composelistview.ui.components.nested_vh_list.PostItems
import com.paragon.usecases_composelistview.ui.components.nested_vh_list.PostType
import com.paragon.usecases_composelistview.ui.components.nested_vh_list.StoryCard

@Composable
fun ViewTypesListScreen() {
    val items = remember { mutableStateListOf<PostItems>() }
    ImagesList.forEachIndexed { index, item ->
        items.add(
            PostItems(
                type = if (index % 2 ==0) PostType.FEED else if(index % 3 == 0) PostType.EVENT else PostType.STORY,
                url = item,
                likes = index,
                isLiked = false,
                userName = "User $index"
            )
        )
    }
    LazyColumn(modifier = Modifier.safeContentPadding()) {                  // Defined both/nested lists in same lazy column to avoid the issues
        items.forEachIndexed { index, postItems ->
            item {
                when(postItems.type) {
                    PostType.EVENT -> {
                        Box(
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth()
                                .height(120.dp)
                                .background(Color.Gray),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Event", color = Color.White, modifier = Modifier
                                .background(Color.Black, shape = RoundedCornerShape(60.dp))
                                .padding(20.dp))
                        }
                    }
                    PostType.FEED -> {
                        Column(modifier = Modifier.padding(top = 10.dp)) {

                            Text(
                                text = "Feed",
                                color = Color.White,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Black)
                                    .padding(10.dp)
                            )

                            AsyncImage(
                                model = items[index].url,
                                contentDescription = items[index].url,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp)
                            )
                        }
                    }
                    PostType.STORY -> {

                    }
                    else -> {

                    }
                }
            }
        }
    }
}