package com.openclassrooms.notes.repository

import com.openclassrooms.notes.data.Note
import com.openclassrooms.notes.service.LocalNotesApiService
import com.openclassrooms.notes.service.NotesApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Repository class for the notes.
 */
class NotesRepository {

    /**
     * The API service for interacting with notes.
     */
    private val notesApiService: NotesApiService = LocalNotesApiService()


    val listNotes = MutableStateFlow<MutableList<Note>>(mutableListOf())


    //val notes = listNotes.asStateFlow()

    init {

        listNotes.value = notesApiService.getAllNotes()
    }

    fun update(title: String, body: String) {

        notesApiService.addNote(title, body)


        listNotes.value = notesApiService.getAllNotes()
    }
}