package com.example.meliapp.activities

import ITEM_CURRENCY
import ITEM_PRICE
import ITEM_QUANTITY
import ITEM_THUMBNAIL
import ITEM_TITLE
import Item
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meliapp.*
import com.example.meliapp.adapters.ItemPagerAdapter
import com.example.meliapp.databinding.ActivityMainBinding
import com.example.meliapp.interfaces.RetrofitService
import com.example.meliapp.repositories.ItemRepository
import com.example.meliapp.viewModels.ItemViewModel
import com.example.meliapp.viewModels.ItemViewModelFactory
import kotlinx.coroutines.launch

//Para este ejercicio se deja como constante el ID del sitio.
const val SITE_ID = "MLA"
const val LOG_APP = "LogApp"

class MainActivity : AppCompatActivity(),
    androidx.appcompat.widget.SearchView.OnQueryTextListener,
    ItemPagerAdapter.OnItemPagerClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var itemViewModel: ItemViewModel
    private lateinit var itemAdapter: ItemPagerAdapter


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        initPagerRecyclerView()

    }

    private fun initPagerRecyclerView() {

        itemAdapter = ItemPagerAdapter(this)

        val retrofitService = RetrofitService.getInstance()
        val mainRepository = ItemRepository(retrofitService)

        binding.rvItems.layoutManager = LinearLayoutManager(this)
        binding.rvItems.adapter = itemAdapter
        binding.svItems.setOnQueryTextListener(this)
        binding.progressbar.isVisible = false

        itemViewModel = ViewModelProvider(
            this,
            ItemViewModelFactory(mainRepository)
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
                    Log.i(LOG_APP,it.error.toString())

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

        intent.putExtra(ITEM_TITLE, item?.title)
        intent.putExtra(ITEM_PRICE, item?.price.toString())
        intent.putExtra(ITEM_CURRENCY, item?.currency_id.toString())
        intent.putExtra(ITEM_QUANTITY, item?.available_quantity.toString())
        intent.putExtra(ITEM_THUMBNAIL, item?.thumbnail)

        startActivity(intent)
    }


}