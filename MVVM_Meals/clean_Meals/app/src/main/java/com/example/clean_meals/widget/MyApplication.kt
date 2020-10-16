package com.example.clean_meals.widget

import android.app.Application
import android.util.Log

class MyApplication : Application() {
    companion object {
        lateinit var prefs: PreferenceUtil 
    }

    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}