package com.nikola.quicknote.fragments

class CreateNoteFragment : NoteEditorFragment() {
    override fun callCrudOperation() {
        viewModel.createNote()
    }
}