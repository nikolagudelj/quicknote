package com.nikola.quicknote.database

import android.content.Context
import androidx.room.Room

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
                        .fallbackToDestructiveMigration()
                        .build()
        }
    }
}