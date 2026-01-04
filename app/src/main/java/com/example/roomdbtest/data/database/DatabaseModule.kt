package com.example.roomdbtest.data.database

import android.content.Context
import com.example.roomdbtest.data.database.dao.CatalogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context // <-- This is the key
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "items_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCatalogDao(db: AppDatabase): CatalogDao {
        return db.catalogDao()
    }
}