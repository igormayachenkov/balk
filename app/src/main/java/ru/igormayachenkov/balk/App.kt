package ru.igormayachenkov.balk

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

private const val TAG = "myapp.app"

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.w(TAG, "onCreate")

    }
}