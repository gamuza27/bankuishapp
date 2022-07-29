package com.example.meliapp.auxiliars

import Item

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.meliapp.activities.SITE_ID
import com.example.meliapp.interfaces.RetrofitService
import com.example.meliapp.repositories.NETWORK_PAGE_SIZE
import java.lang.Exception

class ItemPagingSource(query: String, private val apiService: RetrofitService): PagingSource<Int, Item>() {

    val quertyStr = query

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {

        return try {
            val position = params.key ?: 1
            val response = apiService.getItemsPaging(SITE_ID, quertyStr,position*((position-1)*50), NETWORK_PAGE_SIZE)

            LoadResult.Page(data = response.body()!!.results, prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}