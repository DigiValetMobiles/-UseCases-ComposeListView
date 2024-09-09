package com.paragon.usecases_composelistview.ui.components.half_swipe

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
fun HalfSwipeScreen() {
    val context: Context = LocalContext.current

    val list = remember {
        mutableStateListOf(
            "Swipe Me1", "Swipe Me2", "Swipe Me3", "Swipe Me4", "Swipe Me5"
        )
    }

    Column(
        modifier = Modifier.safeContentPadding()
    ) {
        androidx.compose.material3.Text(
            text = "Swipe right below items",
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
                    SwipeableOptionItem(
                        optionButton = {
                            // region Item Option Button Design
                            Text(
                                text = "Edit",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .background(Color.Blue)
                                    .padding(vertical = 20.dp, horizontal = 10.dp)
                            )
                            // endregion
                        },
                        onDelete = {
                            Log.e("TAG", "delete: $it", )
                            list.removeAt(it)
                        },
                        onOptionButtonRevealed = {
                            Log.e("TAG", "On option button visible: ")
                        },
                        onClickOptionButton = {
                            Toast.makeText(context, "Option Button Clicked...", Toast.LENGTH_LONG)
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