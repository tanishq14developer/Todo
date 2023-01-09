package me.tanishqchawda.todoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.tanishqchawda.todoapp.R
import me.tanishqchawda.todoapp.databinding.ItemCardBinding
import me.tanishqchawda.todoapp.model.NotesModel

class TaskAdapter(
    private val  notesModelList: List<NotesModel>
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private lateinit var binding: ItemCardBinding

    var dataList = emptyList<NotesModel>()
    inner class TaskViewHolder(
        private val binding: ItemCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(notesModel: NotesModel) {
            binding.notesModel = notesModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val largeNews = notesModelList[position]
        binding.card.setOnClickListener{view->
            view.findNavController().navigate(R.id.action_oneFragment_to_twoFragment)
        }
        holder.bind(largeNews)

    }

    override fun getItemCount(): Int = notesModelList.size

    fun setData(toDoData: List<NotesModel>) {
        val toDoDiffUtil = ToDoDiffUtil(dataList, toDoData)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.dataList = toDoData
        toDoDiffResult.dispatchUpdatesTo(this)
    }

}


