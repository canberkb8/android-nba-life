package com.nba.life.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.nba.life.R
import com.nba.life.core.BaseActivity
import com.nba.life.databinding.ActivityMainBinding
import com.nba.life.utils.DialogHelper
import com.nba.life.utils.extensions.changeFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


/**
 * Single activity, all fragments run over this.
 */
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var toggle: ActionBarDrawerToggle

    private var drawer: DrawerLayout? = null
    private var drawerNavView: NavigationView? = null

    var bottomNavView: BottomNavigationView? = null

    var navController: NavController? = null
    lateinit var toolbar: Toolbar

    @Inject
    lateinit var dialogHelper: DialogHelper

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        drawer = viewBinding.drawerLayout
        bottomNavView = viewBinding.bottomNavView
        drawerNavView = viewBinding.drawerNavView


        toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer!!.addDrawerListener(toggle)
        toggle.syncState()

        drawerNavView?.setNavigationItemSelectedListener(drawerNavigationItemSelectedListener)
        bottomNavView?.setOnNavigationItemSelectedListener(navigationItemSelectedListener)

        Timber.i("mainActivity onCreate()")
        navController = Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        );

    }


    //Drawer Menu Actions
    private var drawerNavigationItemSelectedListener =
        NavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_profile -> {
                    this.changeFragment(R.id.profileFragment)
                    Timber.i("nav_profile")
                }
                R.id.nav_example2 -> {
                    Timber.i("nav_example2")
                }
                R.id.nav_example3 -> {
                    Timber.i("nav_example3")
                }
            }
            drawer!!.closeDrawer(GravityCompat.START)
            return@OnNavigationItemSelectedListener true
        }

    //Bottom Navigation Menu Actions
    private var navigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_nav_games -> {
                    Timber.i("bottom_nav_games")
                    this.changeFragment(R.id.gameListFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottom_nav_teams -> {
                    Timber.i("bottom_nav_teams")
                    this.changeFragment(R.id.teamListFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottom_nav_players -> {
                    Timber.i("bottom_nav_players")
                    this.changeFragment(R.id.playerStatsFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottom_nav_stats -> {
                    Timber.i("bottom_nav_stats")
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }


}