package com.example.roomdbtest

// File: app/src/main/java/com/example/roomdbtest/MyApplication.kt



import android.app.Application
import com.example.roomdbtest.data.datastore.UserTokenStore
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.util.UUID

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val userTokenStore = UserTokenStore(this)

        CoroutineScope(Dispatchers.IO).launch {
            userTokenStore.tokenFlow.firstOrNull()?.let { token ->
                if (token == null) {
                    val newToken = UUID.randomUUID().toString()
                    userTokenStore.saveToken(newToken)
                }
            }
        }
    }
}