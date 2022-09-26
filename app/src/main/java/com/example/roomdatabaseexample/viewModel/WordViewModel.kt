package com.example.roomdatabaseexample.viewModel

import androidx.lifecycle.*
import com.example.roomdatabaseexample.data.localDB.Word
import com.example.roomdatabaseexample.data.repository.WordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(private val wordRepository: WordRepository) : ViewModel() {

    val allWords : LiveData<List<Word>> = wordRepository.getAllWords().asLiveData()

    fun insertWord(word: Word) = viewModelScope.launch {
        wordRepository.insertWord(word)
    }

}