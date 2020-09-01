package com.example.runningtracker.ui.tracking

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.runningtracker.datarepository.DataRepository

class TrackingViewModel @ViewModelInject constructor(dataRepository: DataRepository): ViewModel() {
}