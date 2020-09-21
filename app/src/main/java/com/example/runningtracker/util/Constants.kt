package com.example.runningtracker.util

import android.graphics.Color

object Constants {
    const val RUNNING_DB_NAME = "running_db"
    const val LOCATION_PERMISSION_REQUEST_CODE = 101;
    const val ACTION_START_RESUME_LOCATION_TRACKING = "ACTION_START_RESUME_LOCATION_TRACKING"
    const val ACTION_PAUSE_TRACKING = "ACTION_PAUSE_TRACKING"
    const val ACTION_STOP_LOCATION_TRACKING = "ACTION_STOP_LOCATION_TRACKING"
    const val ACTION_SHOW_TRACKING_FRAGMENT = "ACTION_SHOW_TRACKING_FRAGMENT"

    const val LOCATION_UPDATE_INTERVAL = 5000L
    const val FASTEST_LOCATION_INTERVAL = 2000L

    const val POLYLINE_COLOR = Color.RED
    const val POLYLINE_WIDTH = 8f

    const val MAP_ZOOM = 15f

    const val TIMER_UPDATE_INTERVAL = 50L

    const val NOTIFICATION_CHANNEL_ID = "tracking_channel"
    const val NOTIFICATION_CHANEL_NAME = "Tracking"
    const val NOTIFICATION_ID = 1
}