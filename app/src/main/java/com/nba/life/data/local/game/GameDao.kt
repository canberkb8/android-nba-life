package com.nba.life.data.local.game

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GameDao {

    @Insert
    suspend fun insertGames(games: GameEntity)

}