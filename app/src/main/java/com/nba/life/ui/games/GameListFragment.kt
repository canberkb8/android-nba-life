package com.nba.life.ui.games

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nba.life.R
import com.nba.life.data.remote.model.games.GameModel
import com.nba.life.databinding.FragmGameListBinding
import com.nba.life.core.BaseFragment
import com.nba.life.utils.extensions.changeFragment
import com.nba.life.utils.networkHandling.Status
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


/**
 * It shows the list of NBA games.
 */
@AndroidEntryPoint
class GameListFragment : BaseFragment<FragmGameListBinding>() {

    private val gameListViewModel: GameListViewModel by viewModels()
    private val gameListAdapter = GameListAdapter()


    override fun getLayoutRes(): Int {
        return R.layout.fragm_game_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.lifecycleOwner = viewLifecycleOwner

        onGameItemClicked()

        initGamesRecycler()
        initGameListLiveData()
    }


    private fun initGamesRecycler() {
        viewBinding.recyclerGames.apply {
            setHasFixedSize(true)
            adapter = gameListAdapter
            layoutManager = LinearLayoutManager(this@GameListFragment.context)
        }
    }

    private fun initGameListLiveData() {
        gameListViewModel.gameList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { list ->
                        gameListAdapter.gameList = list.data!!
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

    /**
     * Triggered when an item is selected from the gameList.
     */
    private fun onGameItemClicked() {
        gameListAdapter.setOnItemClickListener { gameData: GameModel.GameData? ->
            val action =
                GameListFragmentDirections.actionGameListFragmentToGameDetailFragment(gameData)
            mainAct?.changeFragment(action)
        }
    }


}