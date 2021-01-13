package com.nba.life.data.remote.api

import com.nba.life.data.remote.model.games.GameModel
import com.nba.life.data.remote.model.players.PlayerModel
import com.nba.life.data.remote.model.teams.TeamModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("games")
    suspend fun getRecentGames(): Response<GameModel>

    @GET("teams")
    suspend fun getTeams(): Response<TeamModel>

    @GET("players")
    suspend fun getPlayers(
        @retrofit2.http.Query("page") page: Int,
        @retrofit2.http.Query("per_page") perPage: Int = 100):Response<PlayerModel>
}