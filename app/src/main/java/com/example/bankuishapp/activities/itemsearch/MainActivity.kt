package com.example.bankuishapp.activities.itemsearch

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankuishapp.*
import com.example.bankuishapp.activities.itemsearch.ItemActivity.Companion.ITEM_DESC
import com.example.bankuishapp.activities.itemsearch.ItemActivity.Companion.ITEM_IMAGE
import com.example.bankuishapp.activities.itemsearch.ItemActivity.Companion.ITEM_NAME
import com.example.bankuishapp.activities.itemsearch.ItemActivity.Companion.ITEM_URL
import com.example.bankuishapp.activities.itemsearch.adapters.ItemPagerAdapter
import com.example.bankuishapp.repository.entities.Item
import com.example.bankuishapp.databinding.ActivityMainBinding
import com.example.bankuishapp.activities.itemsearch.viewModels.ItemViewModel
import com.example.bankuishapp.activities.itemsearch.viewModels.ItemViewModelFactory
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(),
    androidx.appcompat.widget.SearchView.OnQueryTextListener,
    ItemPagerAdapter.OnItemPagerClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var itemViewModel: ItemViewModel
    private lateinit var itemAdapter: ItemPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPagerRecyclerView()
        doSearchForKotlin()
    }


    private fun doSearchForKotlin() {
        binding.svItems.setQuery("language:kotlin", true);
    }

    private fun initPagerRecyclerView() {

        itemAdapter = ItemPagerAdapter(this)

        binding.rvItems.layoutManager = LinearLayoutManager(this)
        binding.rvItems.adapter = itemAdapter
        binding.svItems.setOnQueryTextListener(this)
        binding.progressbar.isVisible = false

        binding.swipe.setOnRefreshListener {

            binding.swipe.isRefreshing = false
            doItemsSearch()
        }

        itemViewModel = ViewModelProvider(
            this,
            ItemViewModelFactory()
        ).get(ItemViewModel::class.java)


        itemViewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        itemAdapter.addLoadStateListener { loadState ->
            // show empty list
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            )
                binding.progressbar.isVisible = true
            else {
                binding.progressbar.isVisible = false
                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {

                    Toast.makeText(this, R.string.ErrorBusqueda, Toast.LENGTH_LONG).show()

                }
            }

            if (itemAdapter.itemCount < 1){
                binding.tvBusquedaDesc.visibility = View.VISIBLE
                binding.tvBusquedaDesc.setText(R.string.BusquedaSinResultados)
            }else{
                binding.tvBusquedaDesc.visibility = View.GONE
            }
        }

        if (!itemViewModel.getQuery().isEmpty())
            doItemsSearch()

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            itemViewModel.setQuery(query)
            doItemsSearch()
        }
        return true
    }

    fun doItemsSearch(){
        lifecycleScope.launch {
            itemViewModel.getItemList().observe(this@MainActivity) {
                it?.let {
                    itemAdapter.submitData(lifecycle, it)

                }
            }
        }
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onItemClick(item: Item?) {
        callItemActivity(item)
    }

    fun callItemActivity(item: Item?) {

        val intent = Intent (this, ItemActivity::class.java)

        intent.putExtra(ITEM_NAME, item?.name)
        intent.putExtra(ITEM_DESC, item?.description)
        intent.putExtra(ITEM_IMAGE, item?.owner?.avatar_url)
        intent.putExtra(ITEM_URL, item?.url)
        startActivity(intent)
    }


}