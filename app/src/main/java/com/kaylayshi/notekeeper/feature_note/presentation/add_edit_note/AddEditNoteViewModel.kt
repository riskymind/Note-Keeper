package com.kaylayshi.notekeeper.feature_note.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaylayshi.notekeeper.feature_note.domain.model.Note
import com.kaylayshi.notekeeper.feature_note.domain.usecases.NoteUseCases
import com.kaylayshi.notekeeper.feature_note.presentation.notes.NotesScreenState
import com.kaylayshi.notekeeper.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(AddEditNoteState())
    val state: State<AddEditNoteState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow: SharedFlow<UIEvent> = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        fetchNoteDetail()
    }

    fun onAction(events: AddEditNoteEvents) {
        when (events) {
            is AddEditNoteEvents.EnterContent -> insertNoteContent(events.content)
            is AddEditNoteEvents.EnterTitle -> insertNoteTitle(events.value)
            AddEditNoteEvents.SaveNote -> upsertNote()
        }
    }

    private fun upsertNote() {
        viewModelScope.launch {
            try {
                saveNote()
                _eventFlow.emit(UIEvent.SaveNote)
            } catch (e: Exception) {
                _eventFlow.emit(UIEvent.ShowSnackBar(e.message ?: "couldn't save note"))
            }
        }
    }

    private suspend fun saveNote() {
        noteUseCases.addNote(
            Note(
                title = state.value.title,
                content = state.value.content,
                timeStamp = System.currentTimeMillis(),
                color = 1,
                id = currentNoteId
            )
        )
    }

    private fun insertNoteTitle(value: String) {
        _state.value = _state.value.copy(title = value)
    }

    private fun insertNoteContent(content: String) {
        _state.value = _state.value.copy(content = content)
    }

    private fun fetchNoteDetail() {
        savedStateHandle.get<Int>(key = Constants.NOTE_ID)?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    noteUseCases.getNote(noteId)?.apply {
                        _state.value = state.value.copy(
                            title = title,
                            content = content
                        )
                        currentNoteId = id
                    }
                }
            }
        }
    }
}