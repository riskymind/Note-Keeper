package com.kaylayshi.notekeeper.feature_note.presentation.add_edit_note

sealed class AddEditNoteEvents {
    data class EnterTitle(val value: String) : AddEditNoteEvents()
    data class EnterContent(val content: String) : AddEditNoteEvents()
    object SaveNote : AddEditNoteEvents()
}
