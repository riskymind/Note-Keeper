package com.kaylayshi.notekeeper.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kaylayshi.notekeeper.feature_note.domain.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDB : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        const val DB_NAME = "Note_DB"
    }
}