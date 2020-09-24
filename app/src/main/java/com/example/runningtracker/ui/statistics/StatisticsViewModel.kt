package com.example.runningtracker.ui.statistics

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.runningtracker.datarepository.DataRepository

class StatisticsViewModel @ViewModelInject constructor(val dataRepository: DataRepository): ViewModel() {
}