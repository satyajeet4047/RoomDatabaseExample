package com.example.roomdatabaseexample

import android.app.Application
import com.example.roomdatabaseexample.data.localDB.WordDatabase
import com.example.roomdatabaseexample.data.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy {
        WordDatabase.getDatabase(this,applicationScope)
    }

    val repository by lazy {
        WordRepository(database.wordDao())
    }
}