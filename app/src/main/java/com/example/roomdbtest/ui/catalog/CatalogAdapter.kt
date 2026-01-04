package com.example.roomdbtest.ui.catalog

import android.view.LayoutInflater


import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdbtest.R
import com.example.roomdbtest.data.model.CatalogItem

class CatalogAdapter(
    private var items: List<CatalogItem>,
    private val onAddToCartClicked: (CatalogItem) -> Unit,
    private val onDetailsClicked: (CatalogItem) -> Unit

) : RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>() {


    inner class CatalogViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nameText: TextView = view.findViewById(R.id.itemName)
        val detailsText: TextView = view.findViewById(R.id.itemDetails)
        val priceText: TextView = view.findViewById(R.id.itemPrice)
        val addButton: View = view.findViewById(R.id.addButton)
        val detailsButton: View = view.findViewById(R.id.itemDetailsButton)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_catalog, parent, false)
        return CatalogViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        val item = items[position]
        holder.nameText.text = item.name
        holder.detailsText.text = item.details
        holder.priceText.text = "$${item.price}"

        holder.addButton.setOnClickListener {
            onAddToCartClicked(item)
        }
        holder.detailsButton.setOnClickListener { onDetailsClicked(item) }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<CatalogItem>) {
        this.items = newItems
        notifyDataSetChanged()
    }
}
