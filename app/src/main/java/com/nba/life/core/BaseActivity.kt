package com.nba.life.core

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * All activities get inherited from BaseActivity.
 * Here, you can define the objects you want to access from all activities.
 */
abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var viewBinding: DB

    @LayoutRes
    abstract fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, getLayoutRes())
    }

}