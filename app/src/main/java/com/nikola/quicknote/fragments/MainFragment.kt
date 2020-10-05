package com.nikola.quicknote.fragments

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikola.quicknote.R
import com.nikola.quicknote.adapters.NoteListAdapter
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment() {
    override var layoutId: Int
        get() = R.layout.fragment_main
        set(value) {}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val recyclerView = notes_list
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = NoteListAdapter(viewModel.getNotes().value!!)
        recyclerView.adapter = adapter

        // Observe changes to the note list
        viewModel.getNotes().observe(requireActivity(), Observer {
            if (adapter.itemCount == 0)
                adapter.setNotes(it)
            adapter.notifyItemInserted(adapter.itemCount - 1)
        })

        button.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_createNoteFragment)
        }
    }
    private fun print(text : String) = Log.i("Nikola", text)
}