package com.nba.life.utils.extensions

import android.app.Activity
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.nba.life.R
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * used for changing fragments in the activity with NavDirections
 * @param navAction : interface of combining action id and argument
 */
fun Activity.changeFragment(navAction: NavDirections) {
    this.findNavController(R.id.nav_host_fragment).navigate(navAction)
}

/**
 * used for changing fragments in the activity with Fragment Id
 * @param fragmentId : destination Fragment Id
 */
fun Activity.changeFragment(fragmentId: Int) {
    this.findNavController(R.id.nav_host_fragment).navigate(fragmentId)
}

/**
 * set the toolbar's title
 */
fun Activity.setToolbarTitle(strTitle: String) {
    this.toolbar.title = strTitle
}



