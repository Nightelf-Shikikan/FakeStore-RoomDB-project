package com.example.roomdbtest.ui.catalog



sealed class CatalogServerResult {
    data object Idle : CatalogServerResult()
    data object Loading : CatalogServerResult()
    data object Success : CatalogServerResult()
    data class Error(val message: String) : CatalogServerResult()
}