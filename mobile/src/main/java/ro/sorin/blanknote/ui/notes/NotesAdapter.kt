package ro.sorin.blanknote.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.listitem_note.view.*
import kotlinx.android.synthetic.main.preference_dialog_edittext.view.*
import ro.sorin.blanknote.R
import ro.sorin.blanknote.model.Note

class NotesAdapter(private val notesActionsListener: NoteActionsListener) : RecyclerView.Adapter<NotesAdapter.BlankNoteViewHolder>() {
    private val notes: ArrayList<Note> = ArrayList()

    fun addItem(item: Note) {
        if (notes.map { it.id }.contains(item.id)) return
        notes.add(item)
        notifyItemInserted(notes.indexOf(item))
    }

    fun addItems(items: List<Note>) {
        notes.clear()
        notes.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        notes.clear()
        notifyDataSetChanged()
    }

    fun removeItem(item: Note) {
        val position = notes.indexOf(item)
        notes.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlankNoteViewHolder =
            BlankNoteViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.listitem_note,
                            parent,
                            false))

    override fun onBindViewHolder(holder: BlankNoteViewHolder, position: Int) = holder.bind(notes[position], notesActionsListener)

    inner class BlankNoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val contNote : CardView = view.contNote
        private val tvNoteContent: TextView = view.tvNoteName
        private val editNote: ImageView = view.editNote
        private val deleteNote: ImageView = view.deleteNote

        fun bind(note: Note, notesActionsListener: NoteActionsListener) {
            contNote.setOnClickListener { notesActionsListener.selectNote(note) }
            editNote.setOnClickListener { notesActionsListener.editNote(note) }
            deleteNote.setOnClickListener { notesActionsListener.deleteNote(note) }
            tvNoteContent.text = note.content
        }
    }
}

