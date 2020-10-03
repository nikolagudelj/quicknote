package com.nikola.quicknote.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.nikola.quicknote.dao.NoteDao
import com.nikola.quicknote.database.Database
import com.nikola.quicknote.database.NoteDatabase
import com.nikola.quicknote.model.Note
import kotlinx.coroutines.launch

class MainActivityViewModel(appContext : Application) : AndroidViewModel(appContext) {

    private lateinit var database : NoteDatabase
    private val mutableLiveData = MutableLiveData<MutableList<Note>>()
    private lateinit var notes : MutableList<Note>

    init {
        viewModelScope.launch {
            database = Database.getDatabase(getApplication())
            mutableLiveData.value = database.noteDao().getAll() as MutableList<Note>
            notes = mutableLiveData.value!!     // TODO Might cause a crash
        }
    }

    fun getNotes() : LiveData<MutableList<Note>> {
        return mutableLiveData
    }

    fun createNote(text : String) {
        viewModelScope.launch {
            val createdNote = database.noteDao().create(text)
            notes.add(createdNote)
            mutableLiveData.postValue(notes)
        }
    }
}