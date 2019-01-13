package ro.sorin.blanknote.ui.notes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import ro.sorin.blanknote.api.NotesApi
import ro.sorin.blanknote.model.Note
import ro.sorin.blanknote.utils.CoroutineViewModel
import ro.sorin.blanknote.utils.notesRepository
import ro.sorin.blanknote.utils.retrofit


class NotesViewModel : CoroutineViewModel() {

    internal val notesLiveData: MutableLiveData<List<Note>> = MutableLiveData()
    private val notesService by lazy(LazyThreadSafetyMode.NONE) { retrofit.create(NotesApi::class.java) }

    fun syncNotesWithCloud() {
        launch {
            try {
                val notes = notesService.getNotes().await()
                if (notes.isNotEmpty()) {
                    notesLiveData.postValue(notes)
                }
            } catch (e: Exception) {

            }
        }
    }

    fun getNotes() = notesRepository.getNotes()

    fun addNote(note: Note) {
        launch { notesRepository.addNote(note) }
    }

    fun deleteNote(note: Note) {
        launch {
            try {
                notesRepository.removeNote(note)
            } catch (e: Exception) {

            }
        }
    }
}
