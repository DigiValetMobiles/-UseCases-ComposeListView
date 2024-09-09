package com.paragon.usecases_composelistview.ui.components.nested_vh_list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import coil.compose.AsyncImage
import com.paragon.usecases_composelistview.ImagesList
import com.paragon.usecases_composelistview.R

@Composable
fun NestedVHScreen() {
    val stories = remember { mutableStateOf(ImagesList.mapIndexed { index, s ->  PostItems(PostType.STORY, s, "User $index", 0) }) }
    val posts = remember { mutableStateOf(ImagesList.mapIndexed { index, s -> PostItems(PostType.FEED, s, "User $index", 0) }) }
    val scrollState = rememberScrollState()

    LazyColumn(modifier = Modifier.safeContentPadding()) {                  // Defined both/nested lists in same lazy column to avoid the issues
        //--region Horizontal list
        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(100.dp),
                verticalAlignment = Alignment.Top
            ) {
                item {
                    Card(
                        onClick = { },
                        shape = RoundedCornerShape(35.dp),
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .size(70.dp)
                            .padding(horizontal = 2.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_camera),
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.LightGray)
                                .padding(15.dp)
                        )
                    }
                }
                items(stories.value.size) { index ->
                    StoryCard(size = 70.dp, stories.value[index].url, stories.value[index].userName)
                }
            }
        }
        //--endregion

        item {
            Spacer(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(Color.LightGray)
            )
        }

        //-- region Vertical List
        items(posts.value.size) { index ->
            Column(modifier = Modifier.padding(top = 10.dp)) {
                Box(
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    StoryCard(size = 40.dp, stories.value[index].url, stories.value[index].userName)
                }
                AsyncImage(
                    model = stories.value[index].url,
                    contentDescription = stories.value[index].url,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )
            }
        }
        //--endregion
    }
}

@Composable
fun StoryCard(size: Dp = 80.dp, url: String, userName: String) {
    Column(modifier = Modifier.padding(top = 10.dp)) {
        Card(
            onClick = { },
            shape = RoundedCornerShape(size.div(2)),
            modifier = Modifier
                .padding(end = 2.dp)
                .size(size)
                .border(
                    width = 1.dp,
                    color = Color.DarkGray,
                    shape = RoundedCornerShape(40.dp)
                )
        ) {
            AsyncImage(
                model = url,
                contentDescription = url,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = userName,
            color = Color.Black,
            modifier = Modifier
                .padding(top = 1.dp)
                .width(size),
            textAlign = TextAlign.Center,
            maxLines = 1,
            fontSize = TextUnit(12f, type = TextUnitType.Sp)
        )
    }
}

enum class PostType {
    STORY,
    FEED
}

data class PostItems(
    val type: PostType,
    val url: String,
    val userName: String,
    val likes: Int,
    val isLiked: Boolean = false
)