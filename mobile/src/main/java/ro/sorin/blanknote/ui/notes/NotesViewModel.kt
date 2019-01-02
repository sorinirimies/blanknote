package ro.sorin.blanknote.ui.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ro.sorin.blanknote.api.NotesApi
import ro.sorin.blanknote.model.Note
import ro.sorin.blanknote.utils.notesRepository
import ro.sorin.blanknote.utils.retrofit


class NotesViewModel : ViewModel(), CoroutineScope {

    internal val notesLiveData: MutableLiveData<List<Note>> = MutableLiveData()
    private val notesService by lazy { retrofit.create(NotesApi::class.java) }
    private val job = Job()
    override val coroutineContext = job + Dispatchers.Main

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

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun deleteNote(note: Note) {
        launch {
            try {
                notesRepository.removeNote(note)
            } catch(e: Exception){

            }
        }
    }
}
