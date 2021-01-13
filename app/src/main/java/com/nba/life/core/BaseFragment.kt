package com.nba.life.core

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.nba.life.ui.MainActivity
import com.nba.life.ui.gameDetail.GameDetailFragment
import com.nba.life.ui.games.GameListFragment
import com.nba.life.ui.prelogin.SplashFragment
import com.nba.life.ui.profile.ProfileFragment
import com.nba.life.ui.teams.TeamListFragment
import com.nba.life.utils.extensions.setToolbarTitle
import timber.log.Timber

/**
 * All fragments get inherited from BaseFragment.
 * Here, you can define the objects you want to access from all fragments.
 */
abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {

    var mainAct: MainActivity? = null

    protected lateinit var viewBinding: DB

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainAct = context as MainActivity
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        return viewBinding.root
    }

    override fun onResume() {
        super.onResume()

        when (this) {

            //Drawer Menu Fragments
            is ProfileFragment -> {
                mainAct?.setToolbarTitle("Profile")
                Timber.i("ProfileFragment onViewCreated()")
            }

            //Bottom Navigation Fragments
            is GameListFragment -> {
                mainAct?.setToolbarTitle("Game List")
                Timber.i("GameListFragment onViewCreated()")
            }
            is TeamListFragment -> {
                mainAct?.setToolbarTitle("Team List")
                Timber.i("TeamListFragment onViewCreated()")
            }


            is GameDetailFragment -> {
                mainAct?.setToolbarTitle("Game Detail")
                Timber.i("GameDetailFragment onViewCreated()")
            }
        }
    }

}