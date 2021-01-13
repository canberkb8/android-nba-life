package com.nba.life.utils

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import com.nba.life.R
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


class DialogHelper @Inject constructor(@ActivityScoped private val activity: Activity) {

    private var dialog: Dialog = Dialog(activity)


    init {
        dialog.setContentView(R.layout.dialog_loading_bar)
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
    }

    fun showDialog(){
        dialog.show()
    }

    fun dismissDialog(){
        dialog.dismiss()
    }


}