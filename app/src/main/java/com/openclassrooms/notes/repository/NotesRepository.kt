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


    // MutableStateFlow to hold and emit the list of notes.
    private val listNotes = MutableStateFlow<MutableList<Note>>(mutableListOf())

    // Expose as a read-only Flow.
    val notes = listNotes.asStateFlow()

    init {
        // Initialize the MutableStateFlow with the current list of notes.
        listNotes.value = notesApiService.getAllNotes()
    }

    fun update(title: String, body: String) {
        // Add the note through the API service.
        notesApiService.addNote(title, body)

        // Update the MutableStateFlow with the new list of notes.
        listNotes.value = notesApiService.getAllNotes()
    }
}