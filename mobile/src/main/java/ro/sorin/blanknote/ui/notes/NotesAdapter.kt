package ro.sorin.blanknote.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.listitem_note.view.*
import ro.sorin.blanknote.R
import ro.sorin.blanknote.model.Note


class NotesAdapter : RecyclerView.Adapter<NotesAdapter.BlankNoteViewHolder>() {
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

    override fun onBindViewHolder(holder: BlankNoteViewHolder, position: Int) = holder.bind(notes[position])

    inner class BlankNoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val messageText: TextView = view.tvNoteContent
        fun bind(note: Note) {
            messageText.text = note.content
        }
    }
}

