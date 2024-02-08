package com.openclassrooms.notes

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.openclassrooms.notes.databinding.ActivityMainBinding
import com.openclassrooms.notes.repository.NotesRepository
import com.openclassrooms.notes.widget.NoteItemDecoration
import com.openclassrooms.notes.widget.NoteViewModel
import com.openclassrooms.notes.widget.NotesAdapter
import kotlinx.coroutines.launch

/**
 * The main activity for the app.
 */
class MainActivity : AppCompatActivity() {

    /**
     * The binding for the main layout.
     */
    private lateinit var binding: ActivityMainBinding

    private val notesAdapter = NotesAdapter(mutableListOf())


    private val noteViewModel = NoteViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initFABButton()
        collectNotes()

    }

    /**
     * Collects notes from the repository and updates the adapter.
     */
    private fun collectNotes() {
        lifecycleScope.launch {
            noteViewModel.notesViewModel.collect {
                notesAdapter.updateNotes(it)
            }
        }
    }

    /**
     * Initializes the FAB button.
     */
    private fun initFABButton() {
        binding.btnAdd.setOnClickListener {

            val dialogView = layoutInflater.inflate(R.layout.dialog_add_note, null)
            val editTextTitle = dialogView.findViewById<EditText>(R.id.editTextTitle)
            val editTextBody = dialogView.findViewById<EditText>(R.id.editTextBody)

            MaterialAlertDialogBuilder(this).apply {
                setTitle("Add a new note")
                setView(dialogView)
                setPositiveButton(android.R.string.ok) { dialog, which ->

                    val title = editTextTitle.text.toString()
                    val body = editTextBody.text.toString()

                    noteViewModel.setTitle(title)
                    noteViewModel.setMessage(body)
                    noteViewModel.addNote()
                }
                setNegativeButton(android.R.string.cancel, null)
            }.show()
        }
    }


    /**
     * Initializes the RecyclerView.
     */
    private fun initRecyclerView() {
        with(binding.recycler) {
            addItemDecoration(
                NoteItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.default_margin),
                    resources.getInteger(R.integer.span_count)
                )
            )

            adapter = notesAdapter
        }

    }

}
