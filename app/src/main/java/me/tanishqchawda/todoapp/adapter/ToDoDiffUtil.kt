package me.tanishqchawda.todoapp.adapter

import androidx.recyclerview.widget.DiffUtil
import me.tanishqchawda.todoapp.model.NotesModel

class ToDoDiffUtil(
	private val oldList: List<NotesModel>,
	private val newList: List<NotesModel>
): DiffUtil.Callback() {
	override fun getOldListSize() = oldList.size

	override fun getNewListSize() = newList.size

	override fun areItemsTheSame(
		oldItemPosition: Int,
		newItemPosition: Int
	) = oldList[oldItemPosition] === newList[newItemPosition]

	override fun areContentsTheSame(
		oldItemPosition: Int,
		newItemPosition: Int
	) = oldList[oldItemPosition] == newList[newItemPosition]
}