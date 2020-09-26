package com.example.runningtracker.ui.run

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.runningtracker.datarepository.DataRepository
import com.example.runningtracker.db.Run
import com.example.runningtracker.enums.SortTypes

class RunViewModel @ViewModelInject constructor(private val dataRepository: DataRepository): ViewModel() {
    private val runSortedByDate = dataRepository.getAllRunsSortedByDates()
    private val runsSortedByDistance = dataRepository.getAllRunsSortedByDistance()
    private val runsSortedByCalories = dataRepository.getAllRunsSortedByCalories()
    private val runSortedByRunDuration = dataRepository.getAllRunsSortedByRunDuration()
    private val runSortedByAvgSpeed = dataRepository.getAllRunsSortedByAvgSpeed()


    val runs = MediatorLiveData<List<Run>>()
    var sortType = SortTypes.DATE

    init {
        runs.addSource(runSortedByDate) {result ->
            if(sortType == SortTypes.DATE) {
                result?.let {
                    runs.value = it
                }
            }
        }

        runs.addSource(runsSortedByDistance) {result ->
            if(sortType == SortTypes.DISTANCE) {
                result?.let {
                    runs.value = it
                }
            }
        }

        runs.addSource(runsSortedByCalories) {result ->
            if(sortType == SortTypes.CALORIES_BURNED) {
                result?.let {
                    runs.value = it
                }
            }
        }

        runs.addSource(runSortedByAvgSpeed) {result ->
            if(sortType == SortTypes.AVG_SPEED) {
                result?.let {
                    runs.value = it
                }
            }
        }

        runs.addSource(runSortedByRunDuration) {result ->
            if(sortType == SortTypes.RUNNING_TIME) {
                result?.let {
                    runs.value = it
                }
            }
        }
    }


    fun sortRuns(sortTypes: SortTypes) = when(sortTypes) {
        SortTypes.DATE -> runSortedByDate.value?.let { runs.value = it }
        SortTypes.DISTANCE -> runsSortedByDistance.value?.let { runs.value = it }
        SortTypes.CALORIES_BURNED -> runsSortedByCalories.value?.let { runs.value = it }
        SortTypes.AVG_SPEED -> runSortedByAvgSpeed.value?.let { runs.value = it }
        SortTypes.RUNNING_TIME -> runSortedByRunDuration.value?.let { runs.value = it }
    }.also {
        this.sortType = sortType
    }
}