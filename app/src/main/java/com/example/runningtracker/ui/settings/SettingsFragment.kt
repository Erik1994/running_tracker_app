package com.example.runningtracker.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.runningtracker.R
import com.example.runningtracker.ui.run.RunViewModel
import com.example.runningtracker.util.Constants
import com.example.runningtracker.util.isEmptyField
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_setup.*
import kotlinx.android.synthetic.main.fragment_setup.etName
import kotlinx.android.synthetic.main.fragment_setup.etWeight
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment: Fragment(R.layout.fragment_settings) {
    private val viewModel: SettingsViewModel by viewModels()
    @Inject
    lateinit var sharedPreferences: SharedPreferences


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFields()
        setClickListeners()
    }

    private fun setClickListeners() {
        btnApplyChanges.setOnClickListener{
            if(applyChangesToSharedPref()) {
                showMessage("Changes are saved")
            }
        }
    }

    private fun loadFields() {
        val name = sharedPreferences.getString(Constants.KEY_NAME, "")
        val weight = sharedPreferences.getFloat(Constants.KEY_WEIGHT, 80f)
        etName.setText(name)
        etWeight.setText(weight.toString())
    }


    private fun applyChangesToSharedPref(): Boolean {
        if (etName.isEmptyField() || etWeight.isEmptyField()) {
            showMessage("Please enter all fields")
            return false;
        }
        sharedPreferences.edit()
            .putString(Constants.KEY_NAME, etName.text.toString())
            .putFloat(Constants.KEY_WEIGHT, etWeight.text.toString().toFloat())
            .putBoolean(Constants.KEY_FIRST_TIME_TOGGLE, false)
            .apply()
        val toolbarText = "Let's go, ${etName.text.toString()}"
        requireActivity().tvToolbarTitle.text = toolbarText
        return true
    }

    private fun showMessage(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
    }

}