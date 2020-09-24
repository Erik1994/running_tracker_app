package com.example.runningtracker.ui.setup

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.runningtracker.datarepository.DataRepository

class SetupViewModel @ViewModelInject constructor(val dataRepository: DataRepository): ViewModel() {
}