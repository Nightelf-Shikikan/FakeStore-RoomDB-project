package com.example.roomdbtest.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdbtest.data.model.CatalogItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatalogDao {

    @Query("SELECT * FROM catalog_items")
    fun observeCatalog(): Flow<List<CatalogItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<CatalogItemEntity>)

    @Query("DELETE FROM catalog_items")
    suspend fun clear()
}