package com.nba.life.di

import android.app.Application
import androidx.room.Room
import com.nba.life.data.local.AppDatabase
import com.nba.life.data.local.game.GameDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "NBALife.db"
    ).build()

    @Provides
    @Singleton
    fun provideGameDao(appDatabase: AppDatabase): GameDao = appDatabase.gameDao()

}