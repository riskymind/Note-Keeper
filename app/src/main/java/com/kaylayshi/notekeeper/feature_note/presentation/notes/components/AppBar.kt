package com.kaylayshi.notekeeper.feature_note.presentation.notes.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.kaylayshi.notekeeper.ui.theme.NoteKeeperTheme

@Composable
fun AppBar(
    onToggle: () -> Unit
) {
    TopAppBar(title = {
        Text(
            text = "Your Note",
            style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold)
        )
    }, actions = {
        IconButton(onClick = onToggle) {
            Icon(
                imageVector = Icons.Default.Sort,
                contentDescription = "Sort"
            )
        }
    })
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    NoteKeeperTheme {
        AppBar(onToggle = {})
    }
}