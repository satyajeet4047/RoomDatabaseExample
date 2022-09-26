package com.example.roomdatabaseexample.di

import android.content.Context
import androidx.room.Room
import com.example.roomdatabaseexample.data.localDB.WordDao
import com.example.roomdatabaseexample.data.localDB.WordDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideWordDatabase(@ApplicationContext context: Context) : WordDatabase{
        return Room.databaseBuilder(context, WordDatabase::class.java, "word_database")
            .addMigrations(WordDatabase.MIGRATION_1_2)
            .build()
    }

    @Provides
    @Singleton
    fun provideWordDao(database: WordDatabase) : WordDao {
        return database.wordDao()
    }
}