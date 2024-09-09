package com.paragon.usecases_composelistview.ui.components.edittext_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun EditTextListScreen() {
    val viewModel = EditTextListViewModel()

    val list by viewModel.usersList.observeAsState()

    Column(
        modifier = Modifier.safeContentPadding()
    ) {
        LazyColumn(modifier = Modifier.weight(.9f)) {
            items(
                count = list?.size ?: 0,
                key = { it },
                itemContent = { index ->
                    val item = list?.get(index)
                    val itemName = remember { mutableStateOf(item?.name) }
                    val itemNumber = remember { mutableStateOf(item?.mobileNo) }
                    Column {
                        Text(
                            text = "Details", color = Color.Black, modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .fillMaxWidth()
                        )
                        TextField(
                            value = itemName.value.orEmpty(),
                            placeholder = {
                                Text(
                                    text = "Enter Name",
                                    color = Color.Gray,
                                    modifier = Modifier.fillMaxSize()
                                )
                            },
                            onValueChange = {
                                itemName.value = it
                                viewModel.onNameEntered(enteredName = it, position = index)
                            },
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .fillMaxWidth()
                                .padding(5.dp)
                        )

                        TextField(
                            value = itemNumber.value.orEmpty(),
                            placeholder = {
                                Text(
                                    text = "(+91)-xxxx-xxxxxx",
                                    color = Color.Gray,
                                    modifier = Modifier.fillMaxSize()
                                )
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            onValueChange = {
                                itemNumber.value = it
                                viewModel.onNumberEntered(enteredNumber = it, position = index)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                        )
                    }
                }
            )
        }

        Text(
            text = "Print In Log",
            modifier = Modifier
                .weight(.1f)
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(10.dp).clickable {
                    viewModel.printDetailsInLog()
                },
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}


data class EditableItem(
    var id: Int,
    var name: String = "",
    var mobileNo: String = "",
    var isSelected: Boolean = false
)