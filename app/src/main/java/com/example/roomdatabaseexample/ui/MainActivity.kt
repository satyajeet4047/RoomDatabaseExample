package com.example.roomdatabaseexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.WordApplication
import com.example.roomdatabaseexample.adapter.WordListAdapter
import com.example.roomdatabaseexample.data.localDB.Word
import com.example.roomdatabaseexample.viewModel.WordViewModel
import com.example.roomdatabaseexample.viewModel.WordViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel : WordViewModel by viewModels {
        WordViewModelFactory((application as WordApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
    }

    private fun initUi() {
        val wordListView = findViewById<RecyclerView>(R.id.recyclerview)
        val wordAdapter = WordListAdapter()
        with(wordListView) {
             layoutManager = LinearLayoutManager(context)
            adapter = wordAdapter
        }
        val addButton = findViewById<Button>(R.id.button2)
        val wordTextView= findViewById<EditText>(R.id.et_add_number)
        addButton.setOnClickListener {
            val input = wordTextView.text.takeIf { editable -> !editable.isNullOrEmpty() }
            viewModel.insertWord(Word(input.toString()))
        }
        viewModel.allWords.observe(this){
            it.let { wordAdapter.submitList(it) }
        }

    }


}