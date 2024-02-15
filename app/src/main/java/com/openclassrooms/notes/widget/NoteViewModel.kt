package com.openclassrooms.notes.widget

import androidx.lifecycle.ViewModel
import com.openclassrooms.notes.data.Note
import com.openclassrooms.notes.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import java.lang.IllegalStateException

class NoteViewModel : ViewModel() {

    private val notesRepository = NotesRepository()



    val notesViewModel: Flow<MutableList<Note>> = notesRepository.listNotes

    fun addNote(title : String, body:String) {



        notesRepository.update(title, body)


    }
}