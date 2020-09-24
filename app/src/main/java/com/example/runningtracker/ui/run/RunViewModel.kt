package com.example.runningtracker.ui.run

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.runningtracker.datarepository.DataRepository

class RunViewModel @ViewModelInject constructor(val dataRepository: DataRepository): ViewModel() {

}