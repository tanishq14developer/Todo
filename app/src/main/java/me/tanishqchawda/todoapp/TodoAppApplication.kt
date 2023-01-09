package me.tanishqchawda.todoapp

import android.app.Application
import me.tanishqchawda.todoapp.local.TodoAppDatabase
import me.tanishqchawda.todoapp.local.TodoAppRepository

class TodoAppApplication:Application() {
    private val database by lazy { TodoAppDatabase.getDatabase(this) }
     val repository by lazy { TodoAppRepository(database.todoAppDao()) }

    override fun onCreate() {
        super.onCreate()

    }
}