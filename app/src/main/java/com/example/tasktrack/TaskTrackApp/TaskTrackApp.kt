package com.example.tasktrack.TaskTrackApp

import android.app.Application
import com.google.android.material.color.DynamicColors
import com.google.android.material.color.utilities.DynamicColor
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TaskTrackApp: Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}