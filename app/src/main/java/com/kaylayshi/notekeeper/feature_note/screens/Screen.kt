package com.kaylayshi.notekeeper.feature_note.screens

import com.kaylayshi.notekeeper.feature_note.domain.model.Note
import com.kaylayshi.notekeeper.util.Constants.NOTE_COLOR
import com.kaylayshi.notekeeper.util.Constants.NOTE_ID

sealed class Screen(val route: String) {
    object NoteScreen : Screen("notes_screen")
    object AddEditNoteScreen : Screen(
        "add_edit_note_screen?$NOTE_ID={$NOTE_ID}&$NOTE_COLOR={$NOTE_COLOR}"
    ) {
        fun passNoteParams(note: Note?): String {
            return "add_edit_note_screen?$NOTE_ID=${note?.id}&$NOTE_COLOR=${note?.color}"
        }
    }
}
