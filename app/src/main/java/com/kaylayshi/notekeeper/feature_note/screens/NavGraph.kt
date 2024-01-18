package com.kaylayshi.notekeeper.feature_note.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kaylayshi.notekeeper.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.kaylayshi.notekeeper.feature_note.presentation.notes.NotesScreen
import com.kaylayshi.notekeeper.util.Constants.NOTE_COLOR
import com.kaylayshi.notekeeper.util.Constants.NOTE_ID

@Composable
fun NoteNavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = Screen.NoteScreen.route) {
        composable(route = Screen.NoteScreen.route) {
            NotesScreen(
                onFabClick = { navHostController.navigate(Screen.AddEditNoteScreen.route) },
                onItemClick = {
                    navHostController.navigate(Screen.AddEditNoteScreen.passNoteParams(it))
                }
            )
        }

        composable(
            route = Screen.AddEditNoteScreen.route,
            arguments = listOf(
                navArgument(name = NOTE_ID) {
                    type = NavType.IntType
                    defaultValue = -1
                },

                navArgument(name = NOTE_COLOR) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            AddEditNoteScreen(
                onBackClick = { navHostController.navigateUp() },
                onSave = { navHostController.navigateUp() }
            )
        }
    }
}