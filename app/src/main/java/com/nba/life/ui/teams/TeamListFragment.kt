package com.nba.life.ui.teams

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.nba.life.R
import com.nba.life.data.remote.model.teams.TeamModel
import com.nba.life.databinding.FragmTeamListBinding
import com.nba.life.core.BaseFragment
import com.nba.life.utils.extensions.changeFragment
import com.nba.life.utils.networkHandling.Status
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * ?
 */
@AndroidEntryPoint
class TeamListFragment : BaseFragment<FragmTeamListBinding>() {

    private val teamListAdapter = TeamListAdapter()
    private val teamListViewModel: TeamListViewModel by viewModels()
    lateinit var teamList: MutableList<TeamModel.TeamData>

    override fun getLayoutRes(): Int {
        return R.layout.fragm_team_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.lifecycleOwner = viewLifecycleOwner

        initTeamsRecycler()
        initGameListLiveData()
        initTabLayoutTeams()
        onGameItemClicked()
    }

    private fun initTabLayoutTeams() {
        viewBinding.tabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        Timber.i("All Team")
                        teamListAdapter.teamList = teamList
                    }
                    1 -> {
                        Timber.i("West Team")
                        teamListAdapter.teamList =
                            teamList.filter { it.conference == "West" }.toMutableList()
                    }
                    2 -> {
                        Timber.i("East Team")
                        teamListAdapter.teamList =
                            teamList.filter { it.conference == "East" }.toMutableList()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun initGameListLiveData() {
        teamListViewModel.teamList.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { list ->
                        teamList = list.data!!
                        teamListAdapter.teamList = teamList
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

    private fun initTeamsRecycler() {
        viewBinding.recyclerTeams.apply {
            setHasFixedSize(true)
            adapter = teamListAdapter
            layoutManager = GridLayoutManager(this@TeamListFragment.context, 2)
        }
    }

    private fun onGameItemClicked() {
        teamListAdapter.setOnItemClickListener { teamData: TeamModel.TeamData? ->
            val action =
                TeamListFragmentDirections.actionTeamListFragmentToTeamDetailFragment(teamData)
            mainAct?.changeFragment(action)
        }
    }


}
