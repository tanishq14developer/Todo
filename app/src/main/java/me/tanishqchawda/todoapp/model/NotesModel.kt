package me.tanishqchawda.todoapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class NotesModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val description: String?,
    val priority: String?
)
