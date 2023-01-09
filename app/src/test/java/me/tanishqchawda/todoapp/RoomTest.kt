package me.tanishqchawda.todoapp

import androidx.lifecycle.asFlow
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.*
import me.tanishqchawda.todoapp.local.TodoAppDao
import me.tanishqchawda.todoapp.local.TodoAppDatabase
import me.tanishqchawda.todoapp.model.NotesModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RoomTest {

    private lateinit var database: TodoAppDatabase
    private lateinit var wordsDao: TodoAppDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TodoAppDatabase::class.java
        ).allowMainThreadQueries().build()

        wordsDao = database.todoAppDao()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun insertWord_returnsTrue() = runBlocking {
        val name = NotesModel(1,"ghghjgj","high")
         wordsDao.insertNote(name)
     wordsDao.getNotes().observe() {
            assertThat(it.contains(name)).isTrue()
        }




    }

//    @Test
//    private fun updateWord_returnsTrue() = runBlocking {
//        //insert word
//        val name = Word(id = 1, "Mary")
//        wordsDao.insertWord(name)
//
//        // create updated word
//        val updatedWord = Word(id = 1, "John")
//
//        // update
//        wordsDao.updateWord(updatedWord)
//
//        // get word and assert if it equals to updated word
//        val result = wordsDao.getOneWord(updatedWord.id)
//
//        assertThat(result.name).isEqualTo(updatedWord.name);
//
//    }
//
//
//    @Test
//    fun delete_returnsTrue() = runBlocking {
//        val name = Word(id = 1, "Mary")
//        val secondName = Word(id = 2, "John")
//
//        wordsDao.insertWord(name)
//        wordsDao.insertWord(secondName)
//
//        wordsDao.delete()
//
//        val latch = CountDownLatch(1)
//        val job = async(Dispatchers.IO) {
//            wordsDao.getAllWords().collect {
//                assertThat(it).doesNotContain(name)
//                latch.countDown()
//            }
//        }
//        latch.await()
//        job.cancelAndJoin()
//
//
//    }

}