package com.nikola.quicknote.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikola.quicknote.R
import com.nikola.quicknote.adapters.NoteListAdapter
import com.nikola.quicknote.viewmodel.MainActivityViewModel
import com.nikola.quicknote.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Instantiate ViewModel with custom parameter
        val factory = MainViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(requireActivity(), factory)
            .get(MainActivityViewModel::class.java)

        val recyclerView = notes_list
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = NoteListAdapter(viewModel.getNotes().value!!)
        recyclerView.adapter = adapter

        // Observe changes to the note list
        viewModel.getNotes().observe(requireActivity(), Observer {
            if (adapter.itemCount == 0)
                adapter.setNotes(it)
            adapter.notifyDataSetChanged()
            print("Dataset changed. Number: " + adapter.itemCount)
        })

        var cntr = 1;
        button.setOnClickListener(View.OnClickListener {
            viewModel.createNote("Hello there", "Note no" + cntr++)
            print("Added")
        })
    }
    private fun print(text : String) = Log.i("Nikola", text)
}