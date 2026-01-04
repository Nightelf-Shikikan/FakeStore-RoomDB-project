package com.example.roomdbtest.ui.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdbtest.R
import com.example.roomdbtest.data.model.CatalogItem

class BasketAdapter(
    private var items: List<CatalogItem>,
    private val onRemoveClicked: (CatalogItem) -> Unit
) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    inner class BasketViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.itemName)
        val price = view.findViewById<TextView>(R.id.itemPrice)
        val removeBtn = view.findViewById<Button>(R.id.removeButton)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_basket, parent, false)
        return BasketViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.name
        holder.price.text = "$${item.price}"

        holder.removeBtn.setOnClickListener {
            onRemoveClicked(item)
        }
    }

    override fun getItemCount() = items.size

    fun updateItems(newItems: List<CatalogItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}