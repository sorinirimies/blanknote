package ro.sorin.blanknote.ui.notes

import ro.sorin.blanknote.model.Note

interface NoteActionsListener {
    fun selectNote(note: Note)
    fun deleteNote(note: Note)
    fun editNote(note: Note)
}