package com.kaylayshi.notekeeper.feature_note.presentation.notes

import com.kaylayshi.notekeeper.feature_note.domain.model.Note
import com.kaylayshi.notekeeper.util.NoteOrder

sealed class NotesScreenEvent {
    data class DeleteNote(val note: Note) : NotesScreenEvent()
    data class OrderNote(val noteOrder: NoteOrder) : NotesScreenEvent()
    object RestoreNote : NotesScreenEvent()
    object ToggleOrderSection : NotesScreenEvent()
}
