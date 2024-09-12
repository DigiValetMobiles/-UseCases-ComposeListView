package com.paragon.usecases_composelistview.ui.components.expandable_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ExpandableListViewModel {

    private val _categoryItemList: MutableLiveData<Map<ListItem, List<ListItem>>> = MutableLiveData()
    val categoryItemList: LiveData<Map<ListItem, List<ListItem>>> = _categoryItemList


    init {
        val categoryItemMap = HashMap<ListItem, List<ListItem>>()
        for (i in 1..9) {
            val categoryItems = ArrayList<ListItem>()
            for (j in 1..5) {
                categoryItems.add(ListItem(ListItemType.CHILD, "Item $j"))
            }
            categoryItemMap[ListItem(ListItemType.PARENT, "Category $i", isExpanded = i == 1)] = categoryItems
        }
        setCategoryItemList(categoryItemMap)
    }

    fun setCategoryItemList(value: Map<ListItem, List<ListItem>>) {
        _categoryItemList.value = value
    }

    fun onCategoryExpand(expandedItem: ListItem) {
        val list = categoryItemList.value
        list?.keys?.forEach {
            if (it.name == expandedItem.name)
                it.isExpanded = it.isExpanded.not()
        }
        list?.let {  _categoryItemList.value = it }
    }

    fun onCategoryItemCountUpdated(categoryItem: ListItem, childItem: ListItem, intValue: Int) {
        val list = categoryItemList.value
        list?.entries?.forEach {
            if (it.key.name == categoryItem.name){
                it.value.find { it.name == childItem.name }?.apply {
                    totalCount = intValue
                }
                it.key.totalCount = it.value.sumOf { it.totalCount }
            }
        }
        list?.let {  _categoryItemList.value = it }
    }
}