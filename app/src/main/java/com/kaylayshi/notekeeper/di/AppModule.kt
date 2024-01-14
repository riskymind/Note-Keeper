package com.kaylayshi.notekeeper.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.kaylayshi.notekeeper.feature_note.data.data_source.NoteDB
import com.kaylayshi.notekeeper.feature_note.data.repository.NoteRepositoryImpl
import com.kaylayshi.notekeeper.feature_note.domain.repository.NoteRepository
import com.kaylayshi.notekeeper.feature_note.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDB(@ApplicationContext context: Context): NoteDB {
        return Room.databaseBuilder(context, NoteDB::class.java, NoteDB.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteRepo(db: NoteDB): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteCases(noteRepository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNote = GetNote(noteRepository),
            getNotes = GetNotes(noteRepository),
            addNote = AddNote(noteRepository),
            deleteNote = DeleteNote(noteRepository)
        )
    }

}