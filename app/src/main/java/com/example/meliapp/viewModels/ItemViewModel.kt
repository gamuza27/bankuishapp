package com.example.meliapp.viewModels

import Item
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.meliapp.repositories.ItemRepository


class ItemViewModel constructor(private val mainRepository: ItemRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    //lateinit var itemList: LiveData<PagingData<Item>>
    private var query: String = ""

    fun getItemList(): LiveData<PagingData<Item>> {
        //itemList = mainRepository.getAllItems(query).cachedIn(viewModelScope)
        return mainRepository.getAllItems(query).cachedIn(viewModelScope)
    }

    fun setQuery(queryStr: String){
        query = queryStr
    }

    fun getQuery():String{
        return query
    }

}