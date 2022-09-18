package com.example.bankuishapp.activities.itemsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.bankuishapp.R
import com.example.bankuishapp.databinding.ActivityItemBinding

//Actividad para visualizar un producto, los datos vienen por parametro.
//TODO Utilizar el servicio /items?ids=$ITEM_ID1 para traer toda la info del producto
class ItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemBinding

    companion object {
        val ITEM_NAME = "itemName"
        val ITEM_DESC = "itemDescription"
        val ITEM_IMAGE = "itemImage"
        val ITEM_URL = "itemUrl"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)


        val itemTitle = intent.getStringExtra(ITEM_NAME)
        val itemDesc = intent.getStringExtra(ITEM_DESC)
        val itemImage = intent.getStringExtra(ITEM_IMAGE)
        val itemUrl = intent.getStringExtra(ITEM_URL)

        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvItemTitle.setText(itemTitle)
        binding.tvItemPrice.setText(itemDesc)

        binding.tvItemDesc.setText(itemUrl)

        if (itemImage !== null) {
            Glide.with(this)
                .load(itemImage)
                .into(binding.ivItemImage)
        } else {
            //Todo Buscar una imagen adecuada para productos sin foto.
            binding.ivItemImage.setImageResource(R.drawable.ic_github_ic)
        }

    }

}