package com.nba.life.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nba.life.data.local.game.GameDao
import com.nba.life.data.local.game.GameEntity

@Database(entities = [GameEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao
}