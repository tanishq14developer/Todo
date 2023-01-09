package me.tanishqchawda.todoapp.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.tanishqchawda.todoapp.model.NotesModel
import me.tanishqchawda.todoapp.utils.Converter

@Database(entities = [NotesModel::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class TodoAppDatabase:RoomDatabase() {
    abstract fun todoAppDao(): TodoAppDao

    companion object {

        @Volatile
        private var INSTANCE: TodoAppDatabase? = null

        fun getDatabase(context: Context): TodoAppDatabase {
        /*   if the INSTANCE is not null, then return it,
             if it is, then create the database
        */
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): TodoAppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                TodoAppDatabase::class.java,
                "todoApp_database"
            )
                .build()
        }
    }

//    companion object {
//        @Volatile
//        private var INSTANCE: TodoAppDatabase? = null
//
//        fun getDatabase(context: Context): TodoAppDatabase {
//            val tempInstance = INSTANCE
//            if(tempInstance != null)
//                return tempInstance
//
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    TodoAppDatabase::class.java,
//                    "todoApp_database"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//
//    }
}