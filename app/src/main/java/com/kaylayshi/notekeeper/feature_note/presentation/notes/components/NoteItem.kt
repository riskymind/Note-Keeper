package com.kaylayshi.notekeeper.feature_note.presentation.notes.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kaylayshi.notekeeper.feature_note.domain.model.Note
import com.kaylayshi.notekeeper.ui.theme.NoteKeeperTheme

@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier = Modifier,
    onItemClick: (Note) -> Unit,
    onDelete: () -> Unit
) {

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        DeleteDialog(
            title = "Delete Note",
            message = "Are you sure, you want to delete ${note.title}?",
            onDismiss = { showDialog = false },
            onConfirm = onDelete
        )
    }

    Card(elevation = 4.dp) {
        Box(modifier = modifier
            .testTag("note item")
            .clickable { onItemClick(note) }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(end = 32.dp)
            ) {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = note.content,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis
                )

            }

            IconButton(
                onClick = { showDialog = true },
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Button",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun NoteItemPreview() {
    val note = Note("test1", "test2", 3L, 2)
    NoteKeeperTheme {
        NoteItem(note = note, onItemClick = {}, onDelete = {})
    }
}