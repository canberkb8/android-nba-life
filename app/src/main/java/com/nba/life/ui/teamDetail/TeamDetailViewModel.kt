package com.nba.life.ui.teamDetail

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

class TeamDetailViewModel @ViewModelInject constructor(
    private val apiRepository : ApiRepository,
    private val networkHelper: NetworkHelper
):ViewModel() {
    private val playerListLiveData =  MutableLiveData<Resource<PlayerModel>>()
    val playerList: LiveData<Resource<PlayerModel>> get() = playerListLiveData

    init {
        getPlayerList(1)
    }

    private fun getPlayerList(page:Int) {
        viewModelScope.launch {
            playerListLiveData.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                apiRepository.getPlayers(page).let {
                    if (it.isSuccessful) {
                        playerListLiveData.postValue(Resource.success(it.body()))

                    } else playerListLiveData.postValue(
                        Resource.error(
                            it.errorBody().toString(),
                            null
                        )
                    )
                }
            } else playerListLiveData.postValue(Resource.error("No internet connection", null))
        }
    }
}

