package com.nikola.quicknote.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.nikola.quicknote.R
import com.nikola.quicknote.model.Note
import com.nikola.quicknote.viewmodel.MainActivityViewModel

class NoteListAdapter(private var notes : List<Note>, private val viewModel : MainActivityViewModel) : RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val text: TextView = itemView.findViewById(R.id.text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val note = LayoutInflater.from(parent.context).inflate(R.layout.note_template, parent, false)
        val viewHolder = ViewHolder(note)

        note.setOnClickListener {
            viewModel.currentNote = notes[viewHolder.adapterPosition]
            note.findNavController().navigate(R.id.action_mainFragment_to_editNoteFragment)
        }

        return viewHolder
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = notes[position].text
        holder.title.text = notes[position].title
    }

    fun setNotes(notes : List<Note>) {
        this.notes = notes
    }
}