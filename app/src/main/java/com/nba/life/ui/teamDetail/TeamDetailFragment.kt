package com.nba.life.ui.teamDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.nba.life.R
import com.nba.life.data.remote.model.players.PlayerModel
import com.nba.life.data.remote.model.teams.TeamModel
import com.nba.life.databinding.FragmTeamDetailBinding
import com.nba.life.core.BaseFragment
import com.nba.life.utils.networkHandling.Status
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * ?
 */
@AndroidEntryPoint
class TeamDetailFragment : BaseFragment<FragmTeamDetailBinding>() {

    private val teamDetailViewModel: TeamDetailViewModel by viewModels()
    lateinit var playerList: MutableList<PlayerModel.PlayerData>

    private var teamData: TeamModel.TeamData? = null


    override fun getLayoutRes(): Int {
        return R.layout.fragm_team_detail
    }

    @Override
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.lifecycleOwner = viewLifecycleOwner

        teamData = arguments?.let {
            TeamDetailFragmentArgs.fromBundle(it).argsTeamData
        }

        initSetUi()
    }

    private fun initSetUi() {
        viewBinding.txtTeamName.text = "Team Name : " + teamData?.fullName.toString()
        viewBinding.txtTeamAbbreviation.text =
            "Team Abbreviation : " + teamData?.abbreviation.toString()
        viewBinding.txtTeamConference.text =
            "Team Conference : " + teamData?.conference.toString()
        viewBinding.txtTeamDivision.text = "Team Division : " + teamData?.division.toString()
    }

    private fun initPlayerListLiveData() {
        teamDetailViewModel.playerList
            .observe(viewLifecycleOwner, {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let { list ->
                            Timber.i("SUCCESS : %s", list.data!!)
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


    /**
     * Horiztal team players icon
    private fun initTeamPlayersRecycler() {
    teamDetailBinding.recyclerTeamPlayers.apply {
    setHasFixedSize(true)
    adapter = teamDetailAdapter
    layoutManager = LinearLayoutManager(this@TeamDetailFragment.context,
    LinearLayoutManager.HORIZONTAL,false)
    }
    }
     */
}