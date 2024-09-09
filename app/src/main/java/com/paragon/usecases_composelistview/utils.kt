package com.paragon.usecases_composelistview

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.mutableStateListOf

val ImagesList = mutableStateListOf(
        "https://farm9.staticflickr.com/8505/8441256181_4e98d8bff5_z_d.jpg",
        "https://farm4.staticflickr.com/3075/3168662394_7d7103de7d_z_d.jpg",
        "https://farm2.staticflickr.com/1533/26541536141_41abe98db3_z_d.jpg",
        "https://i.imgur.com/OnwEDW3.jpg",
        "https://farm7.staticflickr.com/6089/6115759179_86316c08ff_z_d.jpg",
        "https://farm2.staticflickr.com/1090/4595137268_0e3f2b9aa7_z_d.jpg",
        "https://farm4.staticflickr.com/3224/3081748027_0ee3d59fea_z_d.jpg",
        "https://farm8.staticflickr.com/7377/9359257263_81b080a039_z_d.jpg",
        "https://farm9.staticflickr.com/8295/8007075227_dc958c1fe6_z_d.jpg",
        "https://farm2.staticflickr.com/1449/24800673529_64272a66ec_z_d.jpg",
        "https://farm4.staticflickr.com/3752/9684880330_9b4698f7cb_z_d.jpg",
        "https://farm4.staticflickr.com/3827/11349066413_99c32dee4a_z_d.jpg",
        "https://i.imgur.com/CzXTtJV.jpg",
        "https://i.imgur.com/OB0y6MR.jpg",
        "https://farm4.staticflickr.com/3852/14447103450_2d0ff8802b_z_d.jpg",
        "https://farm2.staticflickr.com/1533/26541536141_41abe98db3_z_d.jpg",
        "https://farm4.staticflickr.com/3075/3168662394_7d7103de7d_z_d.jpg",
        "https://i.imgur.com/OnwEDW3.jpg",
        "https://farm3.staticflickr.com/2220/1572613671_7311098b76_z_d.jpg",
        "https://farm6.staticflickr.com/5590/14821526429_5c6ea60405_z_d.jpg",
        "https://farm7.staticflickr.com/6089/6115759179_86316c08ff_z_d.jpg",
        "https://farm4.staticflickr.com/3224/3081748027_0ee3d59fea_z_d.jpg",
        "https://farm8.staticflickr.com/7377/9359257263_81b080a039_z_d.jpg",
        "https://farm9.staticflickr.com/8295/8007075227_dc958c1fe6_z_d.jpg",
        "https://farm2.staticflickr.com/1449/24800673529_64272a66ec_z_d.jpg",
        "https://farm4.staticflickr.com/3827/11349066413_99c32dee4a_z_d.jpg",
        "https://farm6.staticflickr.com/5590/14821526429_5c6ea60405_z_d.jpg",
        "https://i.imgur.com/CzXTtJV.jpg",
        "https://i.imgur.com/OB0y6MR.jpg",
        "https://farm3.staticflickr.com/2378/2178054924_423324aac8.jpg",
        "https://farm4.staticflickr.com/3075/3168662394_7d7103de7d_z_d.jpg",
        "https://farm1.staticflickr.com/49/157534104_95ca4e0ae6.jpg ",
        "https://farm4.staticflickr.com/3319/3211138044_9232086442.jpg",
        "https://farm4.staticflickr.com/3049/2327691528_f060ee2d1f.jpg",
        "https://farm3.staticflickr.com/2042/2203964933_f1b80a18ba.jpg",
        "https://farm9.staticflickr.com/8505/8441256181_4e98d8bff5_z_d.jpg",
        "'https://farm4.staticflickr.com/3075/3168662394_7d7103de7d_z_d.jpg",
        "https://farm2.staticflickr.com/1533/26541536141_41abe98db3_z_d.jpg",
        "https://i.imgur.com/OnwEDW3.jpg",
        "https://farm6.staticflickr.com/5590/14821526429_5c6ea60405_z_d.jpg",
        "https://farm7.staticflickr.com/6089/6115759179_86316c08ff_z_d.jpg",
        "https://farm2.staticflickr.com/1090/4595137268_0e3f2b9aa7_z_d.jpg",
        "https://farm4.staticflickr.com/3224/3081748027_0ee3d59fea_z_d.jpg",
        "https://farm4.staticflickr.com/3752/9684880330_9b4698f7cb_z_d.jpg",
        "https://farm4.staticflickr.com/3827/11349066413_99c32dee4a_z_d.jpg",
        "https://i.imgur.com/CzXTtJV.jpg",
        "https://i.imgur.com/OB0y6MR.jpg",
        "https://farm4.staticflickr.com/3852/14447103450_2d0ff8802b_z_d.jpg",
        "https://farm2.staticflickr.com/1533/26541536141_41abe98db3_z_d.jpg",
        "https://farm4.staticflickr.com/3075/3168662394_7d7103de7d_z_d.jpg",
        "https://i.imgur.com/OnwEDW3.jpg",
        "https://farm3.staticflickr.com/2220/1572613671_7311098b76_z_d.jpg",
        "https://farm6.staticflickr.com/5590/14821526429_5c6ea60405_z_d.jpg",
        "https://farm7.staticflickr.com/6089/6115759179_86316c08ff_z_d.jpg",
        "https://farm4.staticflickr.com/3224/3081748027_0ee3d59fea_z_d.jpg",
    )

fun LazyListState.findFirstFullyVisibleItemIndex(): Int = findFullyVisibleItemIndex(false)

fun LazyListState.findLastFullyVisibleItemIndex(): Int = findFullyVisibleItemIndex(true)

fun LazyListState.findFullyVisibleItemIndex(reversed: Boolean): Int {
        layoutInfo.visibleItemsInfo
                .run { if (reversed) reversed() else this }
                .forEach { itemInfo ->
                        val itemStartOffset = itemInfo.offset
                        val itemEndOffset = itemInfo.offset + itemInfo.size
                        val viewportStartOffset = layoutInfo.viewportStartOffset
                        val viewportEndOffset = layoutInfo.viewportEndOffset
                        if (itemStartOffset >= viewportStartOffset && itemEndOffset <= viewportEndOffset) {
                                return itemInfo.index
                        }
                }
        return -1
}