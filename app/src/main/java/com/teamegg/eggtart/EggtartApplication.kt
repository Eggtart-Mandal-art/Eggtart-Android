package com.teamegg.eggtart

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by 노원진 on 2024.03.21
 */

@HiltAndroidApp
class EggtartApplication : Application() {
    init {
        application = this
    }

    companion object {
        lateinit var application: Application
        fun applicationContext(): Context = application.applicationContext
    }
}