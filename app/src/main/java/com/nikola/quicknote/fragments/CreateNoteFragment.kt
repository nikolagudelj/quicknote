package com.nikola.quicknote.fragments

import android.os.Bundle
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.nikola.quicknote.R
import kotlinx.android.synthetic.main.fragment_create_note.*

class CreateNoteFragment : BaseFragment() {
    override var layoutId: Int
        get() = R.layout.fragment_create_note
        set(value) {}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        back_button.setOnClickListener {
            findNavController().popBackStack()
        }

        save_button.setOnClickListener {
            viewModel.createNote("A", "B")
            findNavController().popBackStack()
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
    }


}