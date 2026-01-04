package com.example.roomdbtest.data.model

import android.R.attr.description
import com.example.roomdbtest.data.model.CatalogItemEntity

// Convert entity → UI model
fun CatalogItemEntity.toUi(): CatalogItem {
    return CatalogItem(
        id = id,
        name = name,
        details = details,
        price = price
    )
}

// Convert UI model → entity
fun CatalogItem.toEntity(): CatalogItemEntity {
    return CatalogItemEntity(
        id = id,
        name = name,
        details = details,
        price = price
    )
}