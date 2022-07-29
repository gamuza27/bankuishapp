package com.example.meliapp.repositories

import Item
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.meliapp.auxiliars.ItemPagingSource
import com.example.meliapp.interfaces.RetrofitService

const val NETWORK_PAGE_SIZE = 50

class ItemRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllItems(query: String): LiveData<PagingData<Item>> {

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                ItemPagingSource(query, retrofitService)
            }
            , initialKey = 1
        ).liveData
    }

}