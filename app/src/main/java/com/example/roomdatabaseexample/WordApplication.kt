package com.example.roomdatabaseexample

import android.app.Application
import com.example.roomdatabaseexample.data.localDB.WordDatabase
import com.example.roomdatabaseexample.data.repository.WordRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class WordApplication : Application()