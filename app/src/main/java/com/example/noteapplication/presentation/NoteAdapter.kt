package com.example.noteapplication.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapplication.databinding.NoteItemBinding
import com.example.noteapplication.domain.INoteClick
import com.example.noteapplication.domain.Note

class NoteAdapter(
    val context : Context,
    val InoteClickDelete: INoteClick,
    val InoteClick: INoteClick
):

        RecyclerView.Adapter<NoteAdapter.ViewHolder>(){

    private val allNotes = ArrayList<Note>()

    inner class ViewHolder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root)

    // inside the onCreateViewHolder inflate the view of SingleItemBinding
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
      with(holder){
          with(allNotes[position]){

              holder.binding.tvNote.setText(allNotes.get(position).title)
              holder.binding.tvDate.setText("Last Update: " + allNotes.get(position).timeStamp)
              holder.binding.icDelete.setOnClickListener {
                  InoteClickDelete.onDeleteIconClick(allNotes.get(position))
              }
              holder.itemView.setOnClickListener {
                  InoteClick.onNoteClick(allNotes.get(position))
              }

          }
      }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>) {
        //on below line we are clearing our notes array list/
        allNotes.clear()
        //on below line we are adding a new list to our all notes list.
        allNotes.addAll(newList)
        //on below line we are calling notify data change method to notify our adapter.
        notifyDataSetChanged()
    }


}



