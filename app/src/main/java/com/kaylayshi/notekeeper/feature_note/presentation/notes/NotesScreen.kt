package com.kaylayshi.notekeeper.feature_note.presentation.notes

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kaylayshi.notekeeper.feature_note.domain.model.Note
import com.kaylayshi.notekeeper.feature_note.presentation.notes.components.AppBar
import com.kaylayshi.notekeeper.feature_note.presentation.notes.components.AppFab
import com.kaylayshi.notekeeper.feature_note.presentation.notes.components.NoteItem
import com.kaylayshi.notekeeper.feature_note.presentation.notes.components.OrderSection
import com.kaylayshi.notekeeper.ui.theme.NoteKeeperTheme

@Composable
fun NotesScreen(
    onFabClick: () -> Unit,
    onItemClick: (Note) -> Unit,
    viewModel: NotesScreenViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val state by viewModel.state

    Scaffold(
        topBar = { AppBar(onToggle = { viewModel.onAction(NotesScreenEvent.ToggleOrderSection) }) },
        floatingActionButton = { AppFab(onFabClick = onFabClick) },
        scaffoldState = scaffoldState
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .testTag(""),
                    noteOrder = state.noteOrder,
                    onOrderChange = { order ->
                        viewModel.onAction(NotesScreenEvent.OrderNote(order))
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(state.notes) { note ->
                    NoteItem(
                        note = note,
                        onItemClick = onItemClick,
                        onDelete = {
                            viewModel.onAction(NotesScreenEvent.DeleteNote(note))
                        }
                    )
                }
            }

        }

        if (state.notes.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Add Note \nby pressing add button.",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                )
            }
        }

    }
}


@Preview
@Composable
fun NotesScreenPreview() {
    NoteKeeperTheme {
        NotesScreen(onFabClick = {}, onItemClick = {})
    }
}