package com.nikola.quicknote.fragments

class EditNoteFragment : NoteEditorFragment() {
    override fun callCrudOperation() {
        viewModel.updateNote()
    }
}