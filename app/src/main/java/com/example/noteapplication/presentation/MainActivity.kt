package com.example.noteapplication.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapplication.R
import com.example.noteapplication.databinding.ActivityMainBinding
import com.example.noteapplication.domain.INoteClick
import com.example.noteapplication.domain.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), INoteClick {
        lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //ViewBinding yaratma
        val bindingmain: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingmain.root)

        //ViewModel ile bağlantı
        viewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        //view binding ile xml component tanıtma
        bindingmain.rvNoteItem.layoutManager = LinearLayoutManager(this)

        val noteAdapter = NoteAdapter(this, this, this)
        bindingmain.rvNoteItem.adapter = noteAdapter

        //ViewModel'daki datayı kullanıcıya sunma
        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                noteAdapter.updateList(it)
            }
        })
        //Anasayfada bulunan ekleme butonu bizi AddEditNoteActivity page e yönlendirir.
        bindingmain.btnAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.title} Deleted", Toast.LENGTH_LONG).show()

    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.title)
        intent.putExtra("noteContent", note.content)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
        this.finish()

    }
}