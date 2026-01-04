package com.example.roomdbtest.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import com.example.roomdbtest.data.model.CatalogItem
import com.example.roomdbtest.data.repository.CatalogItemRepository
import com.example.roomdbtest.ui.catalog.CatalogServerResult

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject
import com.example.roomdbtest.data.datastore.UserTokenStore



@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val repository: CatalogItemRepository,
    private val UserTokenStore: UserTokenStore
) : ViewModel() {

    // UI state (loading / error only)
    private val _state = MutableStateFlow<CatalogServerResult>(CatalogServerResult.Idle)
    val state: StateFlow<CatalogServerResult> = _state.asStateFlow()

    // Catalog items now come FROM ROOM
    val items: StateFlow<List<CatalogItem>> =
        repository.catalogItems
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                emptyList()
            )

    // Basket stays UI-only (for now)
    private val _basket = MutableStateFlow<List<CatalogItem>>(emptyList())
    val basket = _basket.asStateFlow()


    fun sendRequest() {
        viewModelScope.launch {
            val token = UUID.randomUUID().toString()
            UserTokenStore.saveToken(token)

            _state.value = CatalogServerResult.Loading

            try {
                repository.refreshCatalog()
                _state.value = CatalogServerResult.Success
            } catch (e: Exception) {
                _state.value = CatalogServerResult.Error("Failed to load catalog")
            }
        }
    }


    fun addToBasket(item: CatalogItem) {
        _basket.value = _basket.value + item
    }

    fun removeFromBasket(item: CatalogItem) {
        _basket.value = _basket.value - item
    }

    fun updateBasketItem(updated: CatalogItem) {
        _basket.value = _basket.value.map {
            if (it.id == updated.id) updated else it
        }
    }


}