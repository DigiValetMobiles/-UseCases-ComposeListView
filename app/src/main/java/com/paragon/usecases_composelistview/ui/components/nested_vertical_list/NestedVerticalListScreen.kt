package com.paragon.usecases_composelistview.ui.components.nested_vertical_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import com.paragon.usecases_composelistview.R

@Composable
fun NestedVerticalListScreen() {

    LazyColumn(modifier = Modifier.safeContentPadding()) {                  // Defined both/nested lists in same lazy column to avoid the issues

        //-- region First List
        item {              // Title
            Text(
                text = "Upcoming Events",
                color = Color.White,
                fontSize = TextUnit(21f, TextUnitType.Sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Black)
                    .padding(10.dp)
            )
        }

        items(5) { index ->            // List items
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(10.dp)
                            .background(color = Color.LightGray)
                            .size(30.dp)
                    )
                    Text(
                        text = "Event ${index + 1}",
                        color = Color.Black,
                        fontSize = TextUnit(16f, TextUnitType.Sp),
                        modifier = Modifier
                            .padding(top = 5.dp)
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 2.dp)
                        .height(0.5.dp)
                        .background(color = Color.LightGray)
                )
            }
        }
        //--endregion

        //-- region Second List
        item {              // Title
            Text(
                text = "Bookings",
                color = Color.White,
                fontSize = TextUnit(21f, TextUnitType.Sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Black)
                    .padding(10.dp)
            )
        }

        items(20) { index ->            // List items
            Column {
                Text(
                    text = "${index + 1} Event Booking",
                    color = Color.Black,
                    fontSize = TextUnit(16f, TextUnitType.Sp),
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 5.dp)
                )
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp)
                    .height(0.5.dp)
                    .background(color = Color.LightGray))
            }
        }
        //--endregion
    }
}