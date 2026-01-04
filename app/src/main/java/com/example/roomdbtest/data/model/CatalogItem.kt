package com.example.roomdbtest.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatalogItem(
    val id: Int,
    val name: String,
    val details: String,
    val price: Double
) : Parcelable