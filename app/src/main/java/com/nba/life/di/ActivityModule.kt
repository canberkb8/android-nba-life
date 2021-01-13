package com.nba.life.di

import android.app.Activity
import com.nba.life.utils.DialogHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    fun provideDialog(activity: Activity): DialogHelper = DialogHelper(activity)
}