package com.openclassrooms.notes.widget

import androidx.lifecycle.ViewModel
import com.openclassrooms.notes.data.Note
import com.openclassrooms.notes.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import java.lang.IllegalStateException
import kotlin.IllegalStateException

class NoteViewModel : ViewModel() {

    private val notesRepository = NotesRepository()


    private var noteTitle:String? = null
    private var noteBody:String? = null


    fun setTitle (title:String ){
        noteTitle = title
    }

    fun setMessage (body: String){
        noteBody = body
    }



    val notesViewModel: Flow<MutableList<Note>> = notesRepository.notes

    fun addNote() {

        val title = noteTitle?: throw IllegalStateException("Title must be set before adding a note !")
        val body = noteBody?: throw  IllegalStateException("Body must be set before adding a note !")

        notesRepository.update(title, body)

        noteTitle = null
        noteBody = null
    }
}