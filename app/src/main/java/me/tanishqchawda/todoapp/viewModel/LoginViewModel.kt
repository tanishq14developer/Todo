package me.tanishqchawda.todoapp.viewModel

import android.app.Application
import android.os.Build
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.tanishqchawda.todoapp.R
import me.tanishqchawda.todoapp.local.TodoAppDatabase
import me.tanishqchawda.todoapp.local.TodoAppRepository
import me.tanishqchawda.todoapp.model.NotesModel
import me.tanishqchawda.todoapp.utils.Priority
import me.tanishqchawda.todoapp.utils.SingleLiveEvent
import java.time.format.DateTimeFormatter

class LoginViewModel(application: Application):AndroidViewModel(application) {
    var check = false

    val todoDao = TodoAppDatabase.getDatabase(getApplication()).todoAppDao()
    val repository = TodoAppRepository(todoDao)
    val getNotes :LiveData<List<NotesModel>> =  repository.allNotes

    var todoget :MutableLiveData<String> = MutableLiveData()
    private var _error:SingleLiveEvent<String> = SingleLiveEvent()
    val error :SingleLiveEvent<String>

    get() = _error




    fun searchDatabase(searchQuery: String) = repository.searchDatabase(searchQuery)



    fun changeTheme(){

        if (!check){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            check = true

        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            check = false

        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun saveFloating(){

        if (todoget.value.isNullOrEmpty()){
            _error.value = "Description can not be empty"
        }else {
            viewModelScope.launch {
                val todoDao = TodoAppDatabase.getDatabase(getApplication()).todoAppDao()
                val repository = TodoAppRepository(todoDao)
                val updatedItem =
                    NotesModel(null ,todoget.value.toString(), "High")

                repository.addNote(updatedItem)
            }
        }
    }




    val listener: AdapterView.OnItemSelectedListener = object:
        AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            when(position) {
                0 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.red))}
                1 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow))}
                2 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green))}
            }
        }

        override fun onNothingSelected(
            parent: AdapterView<*>?
        ) {

        }
    }

    fun verifyDataFromUser(title: String, desc: String): Boolean {
        return !(title.isNullOrEmpty() || desc.isNullOrEmpty())
    }

    fun parsePriorityString(priority: String): Priority {
        return when(priority) {
            "High Priority" -> Priority.High
            "Low Priority" -> Priority.Low
            "Medium Priority" -> Priority.Medium
            else -> Priority.Low
        }
    }




}