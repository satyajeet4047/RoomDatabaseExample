package com.example.roomdatabaseexample.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Word::class],version = 2)
abstract class WordDatabase : RoomDatabase() {

    abstract fun wordDao() : WordDao

    companion object {

         val MIGRATION_1_2 = object : Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE word_table ADD COLUMN synonym TEXT NOT NULL DEFAULT '' ")
            }
        }
    }

}