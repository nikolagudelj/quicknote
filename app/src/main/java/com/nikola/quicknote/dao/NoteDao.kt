package com.nikola.quicknote.dao

import androidx.room.*
import com.nikola.quicknote.model.Note

@Dao
interface NoteDao {
    @Query("select * from note")
    suspend fun getAll() : List<Note>

    @Query("select count(*) from note")
    suspend fun count() : Int

    @Delete
    suspend fun delete(note : Note)

    @Update
    suspend fun update(note : Note)

    @Insert
    suspend fun insert(note : Note)

    /** Generates a new uid for the note, and adds it to the database */
    suspend fun create(text : String, title : String) : Note {
        val note = Note(this.count() + 1, text, title)
        insert(note)
        return note
    }
}