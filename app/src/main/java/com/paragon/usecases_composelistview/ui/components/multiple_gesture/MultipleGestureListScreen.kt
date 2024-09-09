package com.paragon.usecases_composelistview.ui.components.multiple_gesture

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MultipleGestureListScreen() {
    var job: Job? = null
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    val list = remember {
        mutableStateListOf(
            "Item 1", "Item 2", "Item 3", "Item 4", "Item 5"
        )
    }

    Column(
        modifier = Modifier.safeContentPadding()
    ) {
        LazyColumn {
            stickyHeader {
                androidx.compose.material3.Text(
                    text = "Tap below items",
                    color = Color.White,
                    fontSize = TextUnit(21f, TextUnitType.Sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                        .padding(10.dp)
                )
            }
            items(
                count = list.size ?: 0,
                key = {
                    it
                },
                itemContent = { index ->
                    Column(
                        modifier = Modifier
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onDoubleTap = {
                                        job = coroutineScope.showMessage(job, context, "${list[index]} Double Tapped")
                                    },
                                    onLongPress = {
                                        job = coroutineScope.showMessage(job, context, "${list[index]} Long Pressed")
                                    },
                                    onPress = {
                                        job = coroutineScope.showMessage(job, context, "${list[index]} Pressed")
                                    },
                                    onTap = {
                                        job = coroutineScope.showMessage(job, context, "${list[index]} Tapped")
                                    }
                                )
                            }
                    ) {
                        Text(
                            text = list[index],
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(10.dp)
                                .padding(top = 20.dp)
                        )
                        Divider(thickness = 1.dp, color = Color.Gray)
                    }
                }
            )
        }

        LazyColumn(
            modifier = Modifier.padding(top = 10.dp)
        ) {
            stickyHeader {
                androidx.compose.material3.Text(
                    text = "Drag below items",
                    color = Color.White,
                    fontSize = TextUnit(21f, TextUnitType.Sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                        .padding(10.dp)
                )
            }
            items(
                count = list.size ?: 0,
                key = {
                    it
                },
                itemContent = { index ->
                    Column(
                        modifier = Modifier
                            .pointerInput(Unit) {
                                detectDragGestures { _, dragAmount ->
                                    val (x,y) = dragAmount
                                    when {
                                        x > 0 ->{
                                            job = coroutineScope.showMessage(job, context, "${list[index]} Swiped Right")
                                        }
                                        x < 0 ->{
                                            job = coroutineScope.showMessage(job, context, "${list[index]} Swiped Left")
                                        }
                                    }
                                    when {
                                        y > 1 -> {
                                            job = coroutineScope.showMessage(job, context, "${list[index]} Swiped Down")
                                        }
                                        y < -1 -> {
                                            job = coroutineScope.showMessage(job, context, "${list[index]} Swiped Up")
                                        }
                                    }
                                }
                            }
                    ) {
                        Text(
                            text = list[index],
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(10.dp)
                                .padding(top = 20.dp)
                        )
                        Divider(thickness = 1.dp, color = Color.Gray)
                    }
                }
            )
        }
    }
}

fun CoroutineScope.showMessage(job: Job?, context: Context, message: String): Job {
    job?.cancel()
    return launch {
        delay(500)
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}