package com.example.roomdbtest.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore("user_prefs")

@Singleton
class UserTokenStore @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private val USER_TOKEN = stringPreferencesKey("user_token")
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { prefs ->
            prefs[USER_TOKEN] = token
        }
    }

    val tokenFlow: Flow<String?> =
        context.dataStore.data.map { prefs ->
            prefs[USER_TOKEN]
        }
}