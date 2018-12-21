package ro.sorin.blanknote.ui.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ro.sorin.blanknote.model.Note


class NotesViewModel : ViewModel() {
val notes = MutableLiveData<List<Note>>()

    fun getNotes(){

    }
}
