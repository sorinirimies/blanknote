package ro.sorin.blanknote.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ro.sorin.blanknote.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDb : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}