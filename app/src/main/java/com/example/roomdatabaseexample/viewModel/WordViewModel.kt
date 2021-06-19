package com.example.roomdatabaseexample.viewModel

import androidx.lifecycle.*
import com.example.roomdatabaseexample.data.localDB.Word
import com.example.roomdatabaseexample.data.repository.WordRepository
import kotlinx.coroutines.launch

class WordViewModel(private val wordRepository: WordRepository) : ViewModel() {

    val allWords : LiveData<List<Word>> = wordRepository.getAllWords().asLiveData()

    fun insertWord(word: Word) = viewModelScope.launch {
        wordRepository.insertWord(word)
    }

}

class WordViewModelFactory(private val wordRepository: WordRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(wordRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}