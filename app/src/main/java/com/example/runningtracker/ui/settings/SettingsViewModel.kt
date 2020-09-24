package com.example.runningtracker.ui.settings

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.runningtracker.datarepository.DataRepository

class SettingsViewModel @ViewModelInject constructor(val dataRepository: DataRepository): ViewModel() {

}