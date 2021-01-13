package com.nba.life.ui.playerStats

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nba.life.data.remote.model.players.PlayerModel
import com.nba.life.data.repository.ApiRepository
import com.nba.life.utils.networkHandling.NetworkHelper
import com.nba.life.utils.networkHandling.Resource
import kotlinx.coroutines.launch

class PlayerStatsViewModel @ViewModelInject constructor(
    private val apiRepository : ApiRepository,
    private val networkHelper: NetworkHelper
):ViewModel() {
    private val playerStatsListLiveData =  MutableLiveData<Resource<PlayerModel>>()
    val playerStatsList: LiveData<Resource<PlayerModel>> get() = playerStatsListLiveData

    init {
        getPlayerList()
    }

    private fun getPlayerList() {
        viewModelScope.launch {
            playerStatsListLiveData.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()){
                apiRepository.getPlayers(1).let {
                    if (it.isSuccessful) {
                        playerStatsListLiveData.postValue(Resource.success(it.body()))
                    } else playerStatsListLiveData.postValue(
                        Resource.error(
                            it.errorBody().toString(),
                            null
                        )
                    )
                }
            }
        }
    }
}