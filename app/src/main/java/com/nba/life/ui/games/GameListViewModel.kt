package com.nba.life.ui.games

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nba.life.data.local.game.GameDao
import com.nba.life.data.local.game.GameEntity
import com.nba.life.data.remote.model.games.GameModel
import com.nba.life.data.repository.ApiRepository
import com.nba.life.utils.networkHandling.NetworkHelper
import com.nba.life.utils.networkHandling.Resource
import kotlinx.coroutines.launch

class GameListViewModel @ViewModelInject constructor(
    private val apiRepository: ApiRepository,
    private val networkHelper: NetworkHelper,
    private val gameDao: GameDao
) : ViewModel() {

    private val gameListLiveData = MutableLiveData<Resource<GameModel>>()
    val gameList: LiveData<Resource<GameModel>> get() = gameListLiveData


    init {
        getRecentGames()
    }

    //Fetch the last NBA Games.
    private fun getRecentGames() {
        viewModelScope.launch {
            gameListLiveData.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                apiRepository.getRecentGames().let { response ->
                    if (response.isSuccessful) {
                        gameListLiveData.postValue(Resource.success(response.body()))
                        //gameDao.insertGames(gameData)

                    } else gameListLiveData.postValue(
                        Resource.error(
                            response.errorBody().toString(),
                            null
                        )
                    )
                }
            } else gameListLiveData.postValue(Resource.error("No internet connection", null))
        }
    }

}