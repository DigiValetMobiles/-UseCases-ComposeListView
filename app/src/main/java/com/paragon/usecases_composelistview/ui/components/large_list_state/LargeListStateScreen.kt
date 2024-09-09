package com.paragon.usecases_composelistview.ui.components.large_list_state

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text

@Composable
fun LargeListStateScreen() {
    val list = remember { mutableStateListOf<ListItem>() }

    for (i in 1..1000) {
        list.add(ListItem("Item $i"))
    }

    Column(
        modifier = Modifier.safeContentPadding()
    ) {
        androidx.compose.material3.Text(
            text = "Multiple List item state management",
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
                itemContent = { index ->
                    Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp)) {
                        Text(text = list[index].text, color = Color.Black, modifier = Modifier.weight(.9f))
                        Checkbox(
                            checked = list[index].isChecked.value,
                            modifier = Modifier.weight(.1f),
                            onCheckedChange = {
                                if (it) list[index].text += " Checked"
                                else list[index].text = list[index].text.replace(" Checked", "")
                                list[index].isChecked.value = it
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color.Black,
                                uncheckedColor = Color.Gray
                            )
                        )
                    }
                    Divider(thickness = 1.dp, color = Color.Gray)
                }
            )
        }
    }
}

data class ListItem(
    var text: String = "",
    var isChecked: MutableState<Boolean> = mutableStateOf(false)    // To see live updates in list item should use mutable state of items in
)