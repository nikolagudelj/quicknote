package com.nikola.quicknote.fragments

import android.os.Bundle
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.nikola.quicknote.R
import com.nikola.quicknote.model.Note
import kotlinx.android.synthetic.main.fragment_create_note.*
import kotlinx.android.synthetic.main.fragment_edit_note.*
import kotlinx.android.synthetic.main.fragment_edit_note.back_button
import kotlinx.android.synthetic.main.fragment_edit_note.save_button
import kotlinx.android.synthetic.main.fragment_edit_note.text
import kotlinx.android.synthetic.main.fragment_edit_note.title

class EditNoteFragment : CreateNoteFragment() {
    override var layoutId: Int
        get() = R.layout.fragment_edit_note
        set(value) {}

    override fun callCrudOperation() {
        viewModel.updateNote()
    }
}