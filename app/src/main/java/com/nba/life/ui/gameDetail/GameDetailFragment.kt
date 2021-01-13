package com.nba.life.ui.gameDetail

import android.os.Bundle
import android.view.View
import com.nba.life.R
import com.nba.life.data.remote.model.games.GameModel
import com.nba.life.databinding.FragmGameDetailBinding
import com.nba.life.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


/**
 * It shows the detail of specific game.
 */
@AndroidEntryPoint
class GameDetailFragment : BaseFragment<FragmGameDetailBinding>() {


    private var gameData: GameModel.GameData? = null

    override fun getLayoutRes(): Int {
        return R.layout.fragm_game_detail
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.lifecycleOwner = viewLifecycleOwner

        gameData = arguments?.let {
            GameDetailFragmentArgs.fromBundle(it).argsGameData
        }


        viewBinding.textView.text =
            "homeTeam Name : " + gameData?.homeTeam?.fullName.toString()
        viewBinding.textView2.text =
            "Conference : " + gameData?.homeTeam?.conference.toString()

        viewBinding.textView3.text =
            "visitorTeam Name : " + gameData?.visitorTeam?.fullName.toString()
        viewBinding.textView4.text =
            "Conference : " + gameData?.visitorTeam?.conference.toString()
    }


}