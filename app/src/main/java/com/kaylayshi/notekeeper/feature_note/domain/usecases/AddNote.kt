package com.kaylayshi.notekeeper.feature_note.domain.usecases

import com.kaylayshi.notekeeper.feature_note.domain.model.Note
import com.kaylayshi.notekeeper.feature_note.domain.repository.NoteRepository
import com.kaylayshi.notekeeper.util.InvalidNoteException

class AddNote(
    private val noteRepository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("The TITLE of the NOTE cannot be empty")
        }

        if (note.content.isBlank()) {
            throw InvalidNoteException("The CONTENT of the NOTE cannot be empty")
        }

        noteRepository.insertNote(note)
    }

}