package ro.sorin.blanknote.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sorinirimies.kotlinx.alertDialog
import com.sorinirimies.kotlinx.getColorFromRes
import com.sorinirimies.kotlinx.init
import com.sorinirimies.kotlinx.snack
import kotlinx.android.synthetic.main.fragment_notes.*
import ro.sorin.blanknote.R
import ro.sorin.blanknote.model.Note

class NotesFragment : Fragment() {

    private lateinit var viewModel: NotesViewModel
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_notes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
        rvNotes.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            notesAdapter = NotesAdapter(object : NoteActionsListener {
                override fun selectNote(note: Note) {
                    snack(contNotesFragment, note.content)
                }

                override fun deleteNote(note: Note) {
                    showDeleteNoteDialog(note)
                }

                override fun editNote(note: Note) {
                    showEditNoteDialog(note)
                }

            })
            adapter = notesAdapter
        }
        swipeRefreshLayout.apply {
            setColorSchemeColors(
                    context.getColorFromRes(R.color.colorPrimary),
                    context.getColorFromRes(R.color.colorAccent)
            )
            setOnRefreshListener { viewModel.syncNotesWithCloud() }
        }

        viewModel.notesLiveData.observe(this, Observer<List<Note>> { notes ->
            notesAdapter.addItems(notes)
            swipeRefreshLayout.isRefreshing = false
        })
        viewModel.getNotes().observe(this, Observer<List<Note>> {
            notesAdapter.addItems(it)
            swipeRefreshLayout.isRefreshing = false
        })
        fabAddNote.setOnClickListener { showAddNoteDialog() }
    }

    private fun showEditNoteDialog(note: Note) = alertDialog(requireContext()) {
        val v = layoutInflater.init(R.layout.dialog_create_note)
        setView(v)
        setTitle(R.string.edit_note_label)
        setPositiveButton(R.string.edit_note_label) { _, _ ->
            val groupName = v.findViewById<EditText>(R.id.edtNoteContent).text.toString()
            note.content = groupName
            viewModel.addNote(note)
        }
        setNegativeButton(R.string.cancel) { _, _ -> }
    }

    private fun showAddNoteDialog() = alertDialog(requireContext()) {
        val v = layoutInflater.init(R.layout.dialog_create_note)
        setView(v)
        setTitle(R.string.new_note)
        setPositiveButton(R.string.add_note) { _, _ ->
            val noteContent = v.findViewById<EditText>(R.id.edtNoteContent).text.toString()
            viewModel.addNote(Note(content = noteContent))
        }
        setNegativeButton(R.string.cancel) { _, _ -> }
    }

    private fun showDeleteNoteDialog(note: Note) = alertDialog(requireContext()) {
        setTitle(R.string.delete_note_label)
        setPositiveButton(R.string.delete_note) { _, _ -> viewModel.deleteNote(note) }
        setNegativeButton(R.string.cancel) { _, _ -> }
    }

    companion object {
        fun newInstance() = NotesFragment()
        const val TAG_NOTES = "notes-fragment"
    }
}
