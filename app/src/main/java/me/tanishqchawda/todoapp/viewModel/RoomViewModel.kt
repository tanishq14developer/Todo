package me.tanishqchawda.todoapp.viewModel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.tanishqchawda.todoapp.local.TodoAppDatabase
import me.tanishqchawda.todoapp.local.TodoAppRepository
import me.tanishqchawda.todoapp.model.NotesModel
import java.time.format.DateTimeFormatter

class RoomViewModel(application: Application):AndroidViewModel(application) {


    var title :MutableLiveData<String> = MutableLiveData();

    var description:MutableLiveData<String> = MutableLiveData();






    @RequiresApi(Build.VERSION_CODES.O)
    fun save(){

        if (title.value.isNullOrEmpty()){
            return

        }else if (description.value.isNullOrEmpty()){
            return
        }else{
            viewModelScope.launch {

//            val todoAppDatabase=
             TodoAppRepository(TodoAppDatabase.getDatabase(getApplication()).todoAppDao()).addNote(NotesModel(0,title.value.toString(),description.value.toString()))





            }
        }


    }
}