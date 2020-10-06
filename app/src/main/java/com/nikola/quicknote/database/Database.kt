package com.nikola.quicknote.database

import android.content.Context
import androidx.room.Room

object Database {
    fun getDatabase(context: Context) : NoteDatabase {
            return createDatabase(context)
    }

    private fun createDatabase(context: Context) : NoteDatabase {
            return Room.databaseBuilder(context, NoteDatabase::class.java, "note-database")
                    .fallbackToDestructiveMigration()
                    .build()
    }
}