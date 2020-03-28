package com.ocaterinca.ocaterinca

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ocaterinca.ocaterinca.feature.AvatarCardFragment
import com.ocaterinca.ocaterinca.feature.gamecode.GameCodeFragment
import com.ocaterinca.ocaterinca.feature.question.QuestionFragment

class GamePagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int) = when (position) {
        0 -> AvatarCardFragment()
        1 -> GameCodeFragment()
        else -> QuestionFragment()
    }

    override fun getCount() = 3
}