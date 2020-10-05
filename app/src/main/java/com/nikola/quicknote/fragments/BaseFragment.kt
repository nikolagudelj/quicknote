package com.nikola.quicknote.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nikola.quicknote.viewmodel.MainActivityViewModel
import com.nikola.quicknote.viewmodel.MainViewModelFactory

/** Base fragment class for fragments which use the [MainActivityViewModel] */
abstract class BaseFragment : Fragment() {
    abstract  var layoutId : Int    // Redefine for each separate fragment
    protected lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Instantiate ViewModel with custom parameter
        val factory = MainViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(requireActivity(), factory)
            .get(MainActivityViewModel::class.java)
    }
}