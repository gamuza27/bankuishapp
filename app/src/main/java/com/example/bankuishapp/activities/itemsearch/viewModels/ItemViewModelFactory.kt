package com.example.bankuishapp.activities.itemsearch.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ItemViewModelFactory constructor(/*private val repository: ItemRepository*/): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            ItemViewModel() as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}