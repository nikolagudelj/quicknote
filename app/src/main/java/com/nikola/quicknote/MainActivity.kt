package com.nikola.quicknote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.nikola.quicknote.database.Database
import com.nikola.quicknote.database.NoteDatabase
import com.nikola.quicknote.model.Note
import com.nikola.quicknote.viewmodel.MainActivityViewModel
import com.nikola.quicknote.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = MainViewModelFactory(application)
        val viewModel = ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)
        viewModel.getNotes().observe(this, Observer {
            for (note in it) {
                print("${note.uid} ${note.text}")
                print("\n")
            }
        })
        screen.setOnClickListener(View.OnClickListener {
            viewModel.createNote("Hello there")
        })
    }

    private fun print(text : String) {
        Log.i("Nikola", text)
    }
}