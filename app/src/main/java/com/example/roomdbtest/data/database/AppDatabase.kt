package com.example.roomdbtest.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdbtest.data.database.dao.CatalogDao
import com.example.roomdbtest.data.model.CatalogItemEntity

@Database(
    entities = [CatalogItemEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun catalogDao(): CatalogDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "items_database"
                )
                    .fallbackToDestructiveMigration() // ✅ allows clean rebuilds during dev
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}