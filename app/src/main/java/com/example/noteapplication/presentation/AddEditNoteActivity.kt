package com.example.noteapplication.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.noteapplication.R
import com.example.noteapplication.databinding.ActivityAddEditNoteBinding
import com.example.noteapplication.databinding.NoteItemBinding
import com.example.noteapplication.domain.Note
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity() : AppCompatActivity() {

    lateinit var viewModel: NoteViewModel
    var noteID = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityAddEditNoteBinding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )
            .get(NoteViewModel::class.java)


        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteContent = intent.getStringExtra("noteContent")
            noteID = intent.getIntExtra("noteId", -1)
            binding.btnAddEdit.setText("Update Note")
            binding.etNote.setText(noteTitle)
            binding.etContent.setText(noteContent)
        } else {
            binding.btnAddEdit.setText("Save Note")
        }

        binding.btnAddEdit.setOnClickListener {
            val noteTitle = binding.etNote.text.toString()
            val noteContent = binding.etContent.text.toString()

            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteContent.isNotEmpty()) {
                    val simpleDateFormat = SimpleDateFormat("dd MMM,yyyy - HH:mm")
                    val currentDate: String = simpleDateFormat.format(Date())
                    val updatedNote = Note(noteTitle, noteContent, currentDate)
                    updatedNote.id = noteID
                    viewModel.updateNote(updatedNote)
                    Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteContent.isNotEmpty()) {
                    val simpleDateFormat = SimpleDateFormat("dd MMM,yyyy - HH:mm")
                    val currentDate: String = simpleDateFormat.format(Date())
                    viewModel.addNote(Note(noteTitle, noteContent, currentDate))
                    Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_SHORT).show()
                }
            }

            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }

    }//onCreate


}//class