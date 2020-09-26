package com.example.runningtracker.ui.setup

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.runningtracker.R
import com.example.runningtracker.ui.run.RunViewModel
import com.example.runningtracker.util.Constants
import com.example.runningtracker.util.isEmptyField
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_setup.*
import javax.inject.Inject

@AndroidEntryPoint
class SetupFragment : Fragment(R.layout.fragment_setup) {
    private val viewModel: SetupViewModel by viewModels()

    @Inject
    lateinit var sharedPref: SharedPreferences

    // with primitive types cannot use @Inject directly and also lateinit
    @set:Inject
    var isFirsTimeOpen = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(!isFirsTimeOpen) {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.settingsFragment, true).build()
            findNavController().navigate(
                R.id.action_setupFragment_to_runFragment,
                savedInstanceState,
                navOptions
            )
        }
        setClickListeners()
    }

    private fun setClickListeners() {
        continueTextView.setOnClickListener {
            if(writePersonalInfoInSharedPref()) {
                findNavController().navigate(R.id.action_setupFragment_to_runFragment)
            }
        }
    }

    private fun writePersonalInfoInSharedPref(): Boolean {
        if (etName.isEmptyField() || etWeight.isEmptyField()) {
            showErrorMessage()
            return false;
        }
        sharedPref.edit()
            .putString(Constants.KEY_NAME, etName.text.toString())
            .putFloat(Constants.KEY_WEIGHT, etWeight.text.toString().toFloat())
            .putBoolean(Constants.KEY_FIRST_TIME_TOGGLE, false)
            .apply()
        val toolbarText = "Let's go, ${etName.text.toString()}"
        requireActivity().tvToolbarTitle.text = toolbarText
        return true
    }

    private fun showErrorMessage() {
        Snackbar.make(requireView(), "Please enter all fields", Snackbar.LENGTH_LONG).show()
    }

}