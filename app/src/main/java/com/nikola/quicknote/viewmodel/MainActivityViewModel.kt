package com.nikola.quicknote.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.nikola.quicknote.database.Database
import com.nikola.quicknote.database.NoteDatabase
import com.nikola.quicknote.model.Note
import kotlinx.coroutines.launch

class MainActivityViewModel(appContext : Application) : AndroidViewModel(appContext) {

    private val database = Database.getDatabase(getApplication())
    private val mutableLiveData = MutableLiveData<MutableList<Note>>()
    private lateinit var notes : MutableList<Note>
    var currentNote : Note = Note()

    init {
        mutableLiveData.value = mutableListOf()  // Prevent null value before Database gets loaded
        viewModelScope.launch {
            mutableLiveData.value = database.noteDao().getAll() as MutableList<Note>
            notes = mutableLiveData.value!!
        }
    }

    fun getNotes() : LiveData<MutableList<Note>> {
        return mutableLiveData
    }

    fun createNote() {
        viewModelScope.launch {
            val createdNote = database.noteDao().create(currentNote)
            notes.add(createdNote)
            forceLiveDataRefresh()
        }
    }

    fun updateNote() {
        viewModelScope.launch {
            database.noteDao().update(currentNote)
        }
    }

    fun clearCurrentNote() {
        currentNote = Note()
    }

    private fun forceLiveDataRefresh() {
        mutableLiveData.value = notes
    }
}