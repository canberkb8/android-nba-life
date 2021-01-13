package com.nba.life.data.repository

import com.nba.life.data.remote.api.ApiInterface
import javax.inject.Inject

/**
 * Retrofit Request Repository Layer
 */
class ApiRepository @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun getRecentGames() =  apiInterface.getRecentGames()

    suspend fun getTeams() = apiInterface.getTeams()

    suspend fun getPlayers(page:Int) = apiInterface.getPlayers(page)
}