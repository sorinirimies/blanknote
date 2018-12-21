package ro.sorin.blanknote.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.notes_fragment.*
import ro.sorin.blanknote.R
import ro.sorin.blanknote.model.Note

class NotesFragment : Fragment() {

    private lateinit var viewModel: NotesViewModel
    private lateinit var notesAdapter: NotesAdapter
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.notes_fragment, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvNotes.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = notesAdapter
        }
        val notesObserver = Observer<List<Note>> { notes ->
            notesAdapter.addItems(notes)
        }
        viewModel.notesLiveData.observe(this, notesObserver)
    }

    companion object {
        fun newInstance() = NotesFragment()
        const val TAG_NOTES = "notes-fragment"
    }
}
