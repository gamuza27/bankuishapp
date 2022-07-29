package com.example.meliapp.adapters

import Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meliapp.R

class ItemPagerAdapter(listener: OnItemPagerClickListener): PagingDataAdapter<Item, ItemPagerAdapter.ItemViewHolder>(
    ItemComparator
) {

    interface OnItemPagerClickListener {
        fun onItemClick(item: Item?)
    }


    private val listener: OnItemPagerClickListener

    init {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)!!

        holder.bind(item, listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ItemViewHolder(v)
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById<View>(R.id.tvTitleItem) as TextView
        private val price: TextView = itemView.findViewById<View>(R.id.tvPriceItem) as TextView
        private val image: ImageView = itemView.findViewById<View>(R.id.ivItem) as ImageView

        fun bind(item: Item, listener: OnItemPagerClickListener) {
            name.setText(item.title)

            val priceStr = item.price.toString()

            price.setText(item.currency_id + " " + priceStr)

            itemView.setOnClickListener { listener.onItemClick(item) }

            val media = item.thumbnail

            if (media !== null) {
                Glide.with(itemView.context)
                    .load(media)
                    .into(image)
            } else {
                //Icono por defecto cuando no se puede obtener imagen
                image.setImageResource(R.drawable.ic_launcher_background)
            }
        }
    }

    object ItemComparator: DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
}