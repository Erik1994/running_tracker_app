package com.example.runningtracker.ui.statistics

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.runningtracker.datarepository.DataRepository

class StatisticsViewModel @ViewModelInject constructor(private val dataRepository: DataRepository): ViewModel() {

    val totalTimeRun = dataRepository.getTotalDurationInMillis()
    val totalDistance = dataRepository.getTotalDistance()
    val totalCaloriesBurned = dataRepository.getTotalBurnedCalories()
    val totalAvgSpeed = dataRepository.getTotalAverageSpeed()

    val runsSortedByDate = dataRepository.getAllRunsSortedByDates()
}