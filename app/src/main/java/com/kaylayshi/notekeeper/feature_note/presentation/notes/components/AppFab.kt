package com.kaylayshi.notekeeper.feature_note.presentation.notes.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable

@Composable
fun AppFab(
    onFabClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onFabClick,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Note")
    }
}