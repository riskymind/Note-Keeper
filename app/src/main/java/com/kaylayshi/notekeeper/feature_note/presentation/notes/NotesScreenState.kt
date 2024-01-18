package com.kaylayshi.notekeeper.feature_note.presentation.notes

import com.kaylayshi.notekeeper.feature_note.domain.model.Note
import com.kaylayshi.notekeeper.util.NoteOrder
import com.kaylayshi.notekeeper.util.OrderType

data class NotesScreenState(
    val notes:List<Note> = emptyList(),
    val noteOrder:NoteOrder = NoteOrder.Date(OrderType.Ascending),
    val isOrderSectionVisible:Boolean = false
)
