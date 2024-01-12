package com.kaylayshi.notekeeper.feature_note.domain.usecases

data class NoteUseCases(
    val getNotes: GetNotes,
    val getNote: GetNote,
    val addNote: AddNote,
    val deleteNote: DeleteNote
)
