package com.paragon.usecases_composelistview.ui.components.edittext_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class EditTextListViewModel {
    private val _usersList: MutableLiveData<ArrayList<EditableItem>> = MutableLiveData(
        ArrayList<EditableItem>().apply {
            add(EditableItem(0))
            add(EditableItem(1))
            add(EditableItem(2))
            add(EditableItem(3))
            add(EditableItem(4))
            add(EditableItem(5))
            add(EditableItem(6))
            add(EditableItem(7))
            add(EditableItem(8))
            add(EditableItem(9))
            add(EditableItem(10))
        }
    )
    val usersList: LiveData<ArrayList<EditableItem>> = _usersList

    fun onNameEntered(enteredName: String, position: Int) {
        val users = _usersList.value
        val updatedItem = users?.get(position)?.apply {
            this.name = enteredName
        }
        users?.apply {
            updatedItem?.let {
                removeAt(position)
                add(position, it)
                _usersList.value = this
            }
        }
    }

    fun onNumberEntered(enteredNumber: String, position: Int) {
        val users = _usersList.value
        val updatedItem = users?.get(position)?.apply {
            this.mobileNo = enteredNumber
        }
        users?.apply {
            updatedItem?.let {
                removeAt(position)
                add(position, it)
                _usersList.value = this
            }
        }
    }

    fun printDetailsInLog() {
        usersList.value?.forEach {
            Log.e("TAG", "printDetailsInLog: ${it.id}${if (it.name.isNotEmpty()) ", Name- ${it.name}" else ""}${if (it.mobileNo.isNotEmpty())", Number- ${it.mobileNo}" else ""}", )
        }
    }
}