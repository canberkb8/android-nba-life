package com.nba.life.ui.playerStats

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.nba.life.utils.networkHandling.Status
import com.nba.life.R
import com.nba.life.data.remote.model.players.PlayerModel
import com.nba.life.databinding.FragmPlayerStatsBinding
import com.nba.life.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * ?
 */
@AndroidEntryPoint
class PlayerStatsFragment : BaseFragment<FragmPlayerStatsBinding>() {

    private val playerStatsAdapter = PlayerStatsAdapter()
    private val playerStatsViewModel: PlayerStatsViewModel by viewModels()
    lateinit var playerList: List<PlayerModel.PlayerData>

    override fun getLayoutRes(): Int {
        return R.layout.fragm_player_stats
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.lifecycleOwner = viewLifecycleOwner

        initPlayerStatsLiveData()
        initPlayerStatsRecycler()

    }

    private fun initPlayerStatsLiveData() {
        playerStatsViewModel.playerStatsList.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { list ->
                        playerList = list.data!!
                        playerStatsAdapter.playerList = playerList.toMutableList()
                        Timber.i("SUCCESS")
                        mainAct?.dialogHelper?.dismissDialog()
                    }
                }
                Status.LOADING -> {
                    mainAct?.dialogHelper?.showDialog()
                    Timber.w("Loading")
                }
                Status.ERROR -> {
                    mainAct?.dialogHelper?.dismissDialog()
                    Timber.e(it.message)
                }
            }
        })
    }

    private fun initPlayerStatsRecycler() {
        viewBinding.recyclerPlayers.apply {
            setHasFixedSize(true)
            adapter = playerStatsAdapter
            layoutManager = GridLayoutManager(this@PlayerStatsFragment.context, 2)
        }
    }


}