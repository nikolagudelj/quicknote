package com.nikola.quicknote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import com.nikola.quicknote.database.NoteDatabase
import com.nikola.quicknote.model.Note
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Room.databaseBuilder(
            this,
            NoteDatabase::class.java,
            "note-database"
        ).build()

        Log.i("Nikola", "Creating database...\n")
        val noteDao = database.noteDao()

        GlobalScope.launch {
            //noteDao.create(Note(2, "Wassup"))
            //noteDao.create((Note(3, "Yoooo")))

            print("Number of notes: " + noteDao.getNumberOfNotes())
            noteDao.create((Note(3, "Am I added yet")))

            val notes = noteDao.getAll();
            for (note in notes) {
                print(note.text)
            }
        }
    }

    private fun print(text : String) {
        Log.i("Nikola", text)
    }
}