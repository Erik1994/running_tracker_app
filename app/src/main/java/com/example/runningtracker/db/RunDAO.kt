package com.example.runningtracker.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RunDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run: Run)

    @Delete
    suspend fun deleteRun(run: Run)

    @Query("SELECT * FROM running_table ORDER BY run_time DESC")
    fun getAllRunsSortedByDates(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY run_duration DESC")
    fun getAllRunsSortedByRunDuration(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY average_speed DESC")
    fun getAllRunsSortedByAvgSpeed(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY burned_calories DESC")
    fun getAllRunsSortedByCalories(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY distance_meters DESC")
    fun getAllRunsSortedByDistance(): LiveData<List<Run>>

    @Query("SELECT SUM(run_duration) FROM running_table")
    fun getTotalDurationInMillis(): LiveData<Long>

    @Query("SELECT SUM(burned_calories) FROM running_table")
    fun getTotalBurnedCalories(): LiveData<Int>

    @Query("SELECT SUM(distance_meters) FROM running_table")
    fun getTotalDistance(): LiveData<Int>

    @Query("SELECT AVG(average_speed) FROM running_table")
    fun getTotalAverageSpeed(): LiveData<Float>

}