package com.example.bankuishapp.activities.itemsearch.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.bankuishapp.repository.entities.Item
import com.example.bankuishapp.repository.ItemRepository


class ItemViewModel : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    private var query: String = ""
    private val mainRepository = ItemRepository()

    fun getItemList(): LiveData<PagingData<Item>> {
        return mainRepository.getAllItems(query).cachedIn(viewModelScope)
    }

    fun setQuery(queryStr: String){
        query = queryStr
    }

    fun getQuery():String{
        return query
    }

}