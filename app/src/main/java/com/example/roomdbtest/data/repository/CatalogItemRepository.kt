package com.example.roomdbtest.data.repository


import com.example.roomdbtest.data.database.dao.CatalogDao
import com.example.roomdbtest.data.model.CatalogItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import javax.inject.Inject
import kotlin.collections.map
import kotlin.random.Random
import com.example.roomdbtest.data.model.toUi
import com.example.roomdbtest.data.model.toEntity


class CatalogItemRepository @Inject constructor(
    private val dao: CatalogDao
) {

    // UI observes THIS
    val catalogItems: Flow<List<CatalogItem>> =
        dao.observeCatalog()
            .map { entities ->
                entities.map { it.toUi() }
            }

    // Triggered by button click
    suspend fun refreshCatalog() {
        delay(1500) // simulate network

        val serverItems = listOf(
            CatalogItem(1, "Apple", "Fresh red apple", 0.99),
            CatalogItem(2, "Banana", "Ripe yellow banana", 1.20),
            CatalogItem(3, "Orange", "Sweet orange", 1.50),
            CatalogItem(4, "Milk", "1L whole milk", 2.30)
        )

        dao.insertAll(
            serverItems.map { it.toEntity() }
        )
    }
}