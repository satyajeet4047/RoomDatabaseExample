package com.example.roomdatabaseexample.data.repository

import androidx.annotation.WorkerThread
import com.example.roomdatabaseexample.data.localDB.WordDao
import com.example.roomdatabaseexample.data.localDB.Word
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WordRepository @Inject constructor(val wordDao: WordDao) {

     fun getAllWords() : Flow<List<Word>> = wordDao.getWordList()

    @WorkerThread
    suspend fun insertWord(word: Word) {
        wordDao.insertWord(word)
    }
}