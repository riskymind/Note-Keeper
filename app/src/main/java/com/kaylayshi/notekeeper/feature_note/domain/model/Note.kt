package com.kaylayshi.notekeeper.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kaylayshi.notekeeper.ui.theme.*

@Entity(tableName = "note_table")
data class Note(
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}
