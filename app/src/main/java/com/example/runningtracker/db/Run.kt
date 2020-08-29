package com.example.runningtracker.db

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "running_table")
data class Run(
    var img: Bitmap? = null,
    @ColumnInfo(name = "run_time")
    var runTimeStamp: Long = 0L,
    @ColumnInfo(name = "average_speed")
    var averageSpeed: Float = 0f,
    @ColumnInfo(name = "distance_meters")
    var distanceMeters: Int = 0,
    @ColumnInfo(name = "run_duration")
    var runDurationInMillis: Long = 0L,
    @ColumnInfo(name = "burned_calories")
    var caloriesBurned: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}