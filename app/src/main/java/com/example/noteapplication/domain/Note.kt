package com.example.noteapplication.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notesTable")
data class Note(
    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name= "content")
    val content: String,

    @ColumnInfo(name= "timestamp")
    val timeStamp: String
    ){

    @PrimaryKey(autoGenerate = true) var id = 0
}

