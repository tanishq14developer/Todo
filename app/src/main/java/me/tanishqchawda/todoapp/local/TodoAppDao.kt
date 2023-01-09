package me.tanishqchawda.todoapp.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.tanishqchawda.todoapp.model.NotesModel


@Dao
interface TodoAppDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(notesModel: NotesModel)


    @Query("Select * from notes_table")
    fun getNotes():LiveData<List<NotesModel>>

    @Query("SELECT * FROM notes_table WHERE description LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<NotesModel>>




}