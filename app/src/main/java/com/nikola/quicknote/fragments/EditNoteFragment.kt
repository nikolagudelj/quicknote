package com.nikola.quicknote.fragments

class EditNoteFragment : CreateNoteFragment() {
    override fun callCrudOperation() {
        viewModel.updateNote()
    }
}