package com.example.meliapp.activities

import ITEM_CURRENCY
import ITEM_PRICE
import ITEM_QUANTITY
import ITEM_THUMBNAIL
import ITEM_TITLE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.meliapp.R
import com.example.meliapp.databinding.ActivityItemBinding

class ItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val itemTitle = intent.getStringExtra(ITEM_TITLE)
        val itemPrice = intent.getStringExtra(ITEM_PRICE)
        val itemCurrency = intent.getStringExtra(ITEM_CURRENCY)
        val itemQuantity = intent.getStringExtra(ITEM_QUANTITY)
        val itemImage = intent.getStringExtra(ITEM_THUMBNAIL)

        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvItemTitle.setText(itemTitle)
        binding.tvItemPrice.setText(itemCurrency + " " +itemPrice)

        val myStr = String.format(resources.getString(R.string.ItemCantidadDisponible), itemQuantity)
        binding.tvItemDesc.setText(myStr)

        if (itemImage !== null) {
            Glide.with(this)
                .load(itemImage)
                .into(binding.ivItemImage)
        } else {
            binding.ivItemImage.setImageResource(R.drawable.ic_launcher_background)
        }

    }

}