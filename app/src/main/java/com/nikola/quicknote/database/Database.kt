package com.nikola.quicknote.database

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Database {
    companion object {
        private var database : NoteDatabase? = null

        fun getDatabase(context: Context) : NoteDatabase {
            if (database == null) {
                database = createDatabase(context)
            }
            return database!!
        }

        private fun createDatabase(context: Context) : NoteDatabase {
                return Room.databaseBuilder(context, NoteDatabase::class.java, "note-database")
                        .build()
        }
    }
}