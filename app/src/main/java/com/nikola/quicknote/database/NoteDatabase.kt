package com.nikola.quicknote.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nikola.quicknote.dao.NoteDao
import com.nikola.quicknote.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDao
}