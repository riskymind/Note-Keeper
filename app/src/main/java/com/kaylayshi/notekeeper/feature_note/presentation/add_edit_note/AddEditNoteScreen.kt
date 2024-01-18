package com.kaylayshi.notekeeper.feature_note.presentation.add_edit_note

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kaylayshi.notekeeper.feature_note.presentation.add_edit_note.components.NoteDetailFab
import com.kaylayshi.notekeeper.feature_note.presentation.add_edit_note.components.TopBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditNoteScreen(
    onBackClick: () -> Unit,
    onSave: () -> Unit,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val state = viewModel.state.value

    LaunchedEffect(key1 = Unit, block = {
        delay(500)
        focusRequester.requestFocus()
    })

    LaunchedEffect(key1 = Unit) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                UIEvent.SaveNote -> onSave()
                is UIEvent.ShowSnackBar -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Scaffold(
        topBar = { TopBar(onBackClick = onBackClick) },
        floatingActionButton = {
            NoteDetailFab(onFabClick = { viewModel.onAction(AddEditNoteEvents.SaveNote) })
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                value = state.title,
                onValueChange = { newVal ->
                    viewModel.onAction(AddEditNoteEvents.EnterTitle(newVal))
                },
                label = { Text(text = "Title") },
                textStyle = MaterialTheme.typography.body1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Next) }
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                value = state.content,
                onValueChange = { newVal ->
                    viewModel.onAction(AddEditNoteEvents.EnterContent(newVal))
                },
                label = { Text(text = "Content") },
                textStyle = MaterialTheme.typography.body1,
            )
        }
    }
}

@Preview
@Composable
fun AddEditNoteScreenPreview() {}