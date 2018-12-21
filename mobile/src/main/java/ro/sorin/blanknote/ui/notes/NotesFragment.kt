package ro.sorin.blanknote.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import ro.sorin.blanknote.R

class NotesFragment : Fragment() {

    private lateinit var viewModel: NotesViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.notes_fragment, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance() = NotesFragment()
        const val TAG_NOTES = "notes-fragment"
    }
}
