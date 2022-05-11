package com.example.noteapplication.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.noteapplication.data.data_source.NoteDatabase
import com.example.noteapplication.data.repository.NoteRepository
import com.example.noteapplication.domain.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    val allNotes: LiveData<List<Note>>
    val repository: NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(note: Note) = viewModelScope.launch (Dispatchers.IO){
        repository.delete(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch (Dispatchers.IO){
        repository.update(note)
    }

    fun addNote(note: Note)= viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }


}