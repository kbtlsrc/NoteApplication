package com.example.noteapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.noteapplication.data.data_source.NoteDao
import com.example.noteapplication.domain.Note

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }

    suspend fun delete(note: Note){
        noteDao.delete(note)
    }

    suspend fun update(note: Note){
        noteDao.update(note)
    }

}