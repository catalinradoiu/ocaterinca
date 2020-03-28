package com.ocaterinca.ocaterinca

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ocaterinca.ocaterinca.feature.AvatarCardFragment

class GamePagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return AvatarCardFragment()
    }

    override fun getCount() = 1
}