package com.example.runningtracker.ui.tracking

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runningtracker.datarepository.DataRepository
import com.example.runningtracker.db.Run
import kotlinx.coroutines.launch

class TrackingViewModel @ViewModelInject constructor(val dataRepository: DataRepository): ViewModel() {

    fun insertRun(run: Run) = viewModelScope.launch {
        dataRepository.insertRun(run)
    }
}