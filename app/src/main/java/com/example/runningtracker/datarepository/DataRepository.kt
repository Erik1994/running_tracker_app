package com.example.runningtracker.datarepository

import com.example.runningtracker.db.Run
import com.example.runningtracker.db.RunDAO
import javax.inject.Inject

class DataRepository @Inject constructor(private val runDAO: RunDAO) {

    suspend fun insertRun(run: Run) = runDAO.insertRun(run)

    suspend fun deleteRun(run: Run) = runDAO.deleteRun(run)

    fun getAllRunsSortedByDates() = runDAO.getAllRunsSortedByDates()

    fun getAllRunsSortedByRunDuration() = runDAO.getAllRunsSortedByRunDuration()

    fun getAllRunsSortedByAvgSpeed() = runDAO.getAllRunsSortedByAvgSpeed()

    fun getAllRunsSortedByCalories() = runDAO.getAllRunsSortedByCalories()

    fun getAllRunsSortedByDistance() = runDAO.getAllRunsSortedByDistance()

    fun getTotalDurationInMillis() = runDAO.getTotalDurationInMillis()

    fun getTotalBurnedCalories() = runDAO.getTotalBurnedCalories()

    fun getTotalDistance() = runDAO.getTotalDistance()

    fun getTotalAverageSpeed() = runDAO.getTotalAverageSpeed()
}