package com.openclassrooms.notes.service

import com.openclassrooms.notes.data.Note

/**
 * Defines the contract for the API that manages the notes
 */
interface NotesApiService {



    /**
     * Add a note

     */
    fun addNote(title:String,body:String)

    /**
     * Returns all the notes
     * @return the list of notes
     */
    fun getAllNotes(): MutableList<Note>

}