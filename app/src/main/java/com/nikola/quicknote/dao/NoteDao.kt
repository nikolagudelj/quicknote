package com.nikola.quicknote.dao

import androidx.room.*
import com.nikola.quicknote.model.Note

@Dao
interface NoteDao {
    @Query("select * from note")
    fun getAll() : List<Note>

    @Query("select count(*) from note")
    fun getNumberOfNotes() : Int

    @Delete
    fun delete(note : Note)

    @Update
    fun update(note : Note)

    @Insert
    fun create(note : Note)
}