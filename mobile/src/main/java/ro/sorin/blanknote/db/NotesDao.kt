package ro.sorin.blanknote.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ro.sorin.blanknote.model.Note

@Dao
interface NotesDao {

    @Query("SELECT * FROM note")
    fun getAll(): List<Note>

    @Query("SELECT * FROM note WHERE uid IN (:uids)")
    fun loadAllByIds(uids: IntArray): List<Note>

    @Query("SELECT * FROM note WHERE content LIKE :contentName  LIMIT 1")
    fun findByName(contentName: String): Note

    @Insert
    fun insertAll(vararg note: Note)

    @Delete
    fun delete(note: Note)
}