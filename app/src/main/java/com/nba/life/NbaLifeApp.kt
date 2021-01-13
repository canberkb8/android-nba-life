package com.nba.life

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class NbaLifeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        //log only on the debug build
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}