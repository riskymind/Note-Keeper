package com.kaylayshi.notekeeper.feature_note.presentation.add_edit_note

sealed class UIEvent {
    data class ShowSnackBar(val message: String) : UIEvent()
    object SaveNote : UIEvent()
}