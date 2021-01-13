package com.nba.life.ui.teams

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nba.life.data.remote.model.teams.TeamModel
import com.nba.life.data.repository.ApiRepository
import com.nba.life.utils.networkHandling.NetworkHelper
import com.nba.life.utils.networkHandling.Resource
import kotlinx.coroutines.launch

class TeamListViewModel @ViewModelInject constructor(
    private val apiRepository : ApiRepository,
    private val networkHelper: NetworkHelper
): ViewModel() {
    private val teamListLiveData =  MutableLiveData<Resource<TeamModel>>()
    val teamList: LiveData<Resource<TeamModel>> get() = teamListLiveData

    init {
        getTeamList()
    }

    private fun getTeamList() {
        viewModelScope.launch {
            teamListLiveData.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                apiRepository.getTeams().let {
                    if (it.isSuccessful) {
                        teamListLiveData.postValue(Resource.success(it.body()))
                    } else teamListLiveData.postValue(
                        Resource.error(
                            it.errorBody().toString(),
                            null
                        )
                    )
                }
            } else teamListLiveData.postValue(Resource.error("No internet connection", null))
        }
    }
}
