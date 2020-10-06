package com.nikola.quicknote.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.nikola.quicknote.R
import com.nikola.quicknote.model.Note
import kotlinx.android.synthetic.main.fragment_note_editor.*

abstract class NoteEditorFragment : BaseFragment() {
    override var layoutId: Int
        get() = R.layout.fragment_note_editor
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
        // Override default back action, so the app doesn't close
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            goBack()
        }

        // Auto bulleting for note text
        text.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (switch_button.isChecked  && s != null
                        &&  s.length > 1
                        &&  s[start + count - 1] == ' '  &&  s[start + count - 2] == ' ')
                {
                    text.removeTextChangedListener(this)
                    val string = s.toString()
                    val changedString = string.replace("  ", "\n\u2022 ", true)
                    text.setText(changedString)
                    text.addTextChangedListener(this)
                    text.setSelection(start + count + 1)
                }
            }
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
        })
    }

    private fun callSaveButtonAction() {
        updateCurrentNote()
        callCrudOperation()
        goBack()
    }

    protected abstract fun callCrudOperation()

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
