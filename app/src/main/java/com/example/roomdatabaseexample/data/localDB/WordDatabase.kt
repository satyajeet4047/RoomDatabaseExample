package com.example.roomdatabaseexample.data.localDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Word::class],version = 2)
abstract class WordDatabase : RoomDatabase() {

    abstract fun wordDao() : WordDao

    companion object {
        @Volatile
        private var INSTANCE : WordDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope) : WordDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, WordDatabase::class.java, "word_database")
                    .addMigrations(MIGRATION_1_2)
                    .addCallback(WordDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                instance
            }
        }

        private val MIGRATION_1_2 = object : Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE word_table ADD COLUMN synonym TEXT NOT NULL DEFAULT '' ")
            }
        }
    }




    private class WordDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var wordDao = database.wordDao()

                    // Delete all content here.
                    wordDao.deleteAll()

                    // Add sample words.
                    var word: Word = Word(1,"Hello")
                    wordDao.insertWord(word)
                    word = Word(2,"World!")
                    wordDao.insertWord(word)

                    word = Word(3,"TODO!")
                    wordDao.insertWord(word)
                }
            }
        }
    }
}