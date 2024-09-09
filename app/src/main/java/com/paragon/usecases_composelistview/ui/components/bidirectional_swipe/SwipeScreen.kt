package com.paragon.usecases_composelistview.ui.components.bidirectional_swipe

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text

@Composable
fun SwipeScreen() {
    val context: Context = LocalContext.current
    val list = remember {
        mutableStateListOf(
            "Bi Swipe Option 1", "Bi Swipe Option 2", "Bi Swipe Option 3", "Bi Swipe Option 4", "Bi Swipe Option 5"
        )
    }

    Column(
        modifier = Modifier.safeContentPadding()
    ) {
        androidx.compose.material3.Text(
            text = "Swipe below items Right/Left",
            color = Color.White,
            fontSize = TextUnit(21f, TextUnitType.Sp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(10.dp)
        )
        LazyColumn() {
            items(
                count = list.size ?: 0,
                key = {
                    it
                },
                itemContent = {
                    BiDirectionalSwipeableOptions(
                        startOptionButton = {
                            // region Left Option Button Design
                            Text(
                                text = "Start",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .background(Color.Blue)
                                    .padding(vertical = 20.dp, horizontal = 10.dp)
                            )
                            // endregion
                        },
                        onStartButtonRevealed = {
                            Log.e("TAG", "Start button visible: ", )
                        },
                        onClickStartOptionButton = {
                            Toast.makeText(context, "Start Option Button Clicked...", Toast.LENGTH_LONG)
                                .show()
                        },
                        endOptionButton = {
                            // region End Option Button Design
                            Text(
                                text = "End",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .background(Color.Red)
                                    .padding(vertical = 20.dp, horizontal = 10.dp)
                            )
                            // endregion
                        },
                        onEndButtonRevealed = {
                            Log.e("TAG", "End button visible: ", )
                        },
                        onClickEndOptionButton = {
                            Toast.makeText(context, "End Option Button Clicked...", Toast.LENGTH_LONG)
                                .show()
                        },
                        content = {
                            // region List Item Design
                            Text(
                                text = list[it],
                                color = Color.Black,
                                textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White)
                                    .padding(10.dp)
                                    .padding(top = 20.dp)
                            )
                            // endregion
                        }
                    )
                    Divider(thickness = 1.dp, color = Color.Gray)
                }
            )
        }
    }
}