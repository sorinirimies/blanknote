package ro.sorin.blanknote.db

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import ro.sorin.blanknote.model.Note

class NotesRepository constructor(private val notesDao: NotesDao) {

    suspend fun addNote(note: Note) {
        withContext(IO) {
            notesDao.insertAll(note)
        }
    }

    fun getNotes() = notesDao.getAll()

    suspend fun removeNote(note: Note) = withContext(IO) {
        notesDao.delete(note)
    }

}

