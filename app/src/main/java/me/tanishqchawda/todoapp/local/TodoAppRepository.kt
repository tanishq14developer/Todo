package me.tanishqchawda.todoapp.local

import androidx.lifecycle.LiveData
import me.tanishqchawda.todoapp.model.NotesModel

class TodoAppRepository(private val todoAppDao: TodoAppDao) {

    suspend fun addNote(notesModel: NotesModel ) = todoAppDao.insertNote(notesModel)

    val allNotes: LiveData<List<NotesModel>> = todoAppDao.getNotes()
    fun searchDatabase(searchQuery: String) = todoAppDao.searchDatabase(searchQuery)
}