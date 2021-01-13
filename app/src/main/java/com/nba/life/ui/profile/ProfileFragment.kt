package com.nba.life.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.nba.life.R
import com.nba.life.databinding.FragmProfileBinding
import com.nba.life.core.BaseFragment
import com.nba.life.databinding.FragmTeamDetailBinding

class ProfileFragment : BaseFragment<FragmProfileBinding>() {

    override fun getLayoutRes(): Int {
        return R.layout.fragm_profile
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.lifecycleOwner = viewLifecycleOwner
    }

}