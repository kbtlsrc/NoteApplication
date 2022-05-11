package com.example.noteapplication.domain

interface INoteClick {

    fun onNoteClick(note: Note)

    fun onDeleteIconClick(note: Note)
}