package com.nikola.quicknote.fragments

import android.os.Bundle
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.nikola.quicknote.R
import com.nikola.quicknote.model.Note
import kotlinx.android.synthetic.main.fragment_create_note.*
import kotlinx.android.synthetic.main.fragment_create_note.back_button
import kotlinx.android.synthetic.main.fragment_create_note.save_button
import kotlinx.android.synthetic.main.fragment_create_note.text
import kotlinx.android.synthetic.main.fragment_create_note.title
import kotlinx.android.synthetic.main.fragment_edit_note.*

open class CreateNoteFragment : BaseFragment() {
    override var layoutId: Int
        get() = R.layout.fragment_create_note
        set(value) {}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        displayNote(viewModel.currentNote)
        back_button.setOnClickListener {
            goBack()
        }
        save_button.setOnClickListener {
            callSaveButtonAction()
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            goBack()
        }
    }

    private fun callSaveButtonAction() {
        updateCurrentNote()
        callCrudOperation()
        goBack()
    }

    protected open fun callCrudOperation() {
        viewModel.createNote()
    }

    private fun displayNote(note : Note) {
        title.setText(note.title)
        text.setText(note.text)
    }

    private fun updateCurrentNote() : Note  {
        viewModel.currentNote.title = title.text.toString()
        viewModel.currentNote.text = text.text.toString()

        return viewModel.currentNote
    }

    private fun goBack() {
        viewModel.clearCurrentNote()
        findNavController().popBackStack()
    }
}