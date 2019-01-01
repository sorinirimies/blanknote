package ro.sorin.blanknote.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ro.sorin.blanknote.model.Note

@Dao
interface NotesDao {

    @Query("SELECT * FROM note")
    fun getAll(): LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE content LIKE :contentName  LIMIT 1")
    fun findByName(contentName: String): LiveData<Note>

    @Insert
    fun insertAll(vararg note: Note)

    @Delete
    fun delete(note: Note)
}