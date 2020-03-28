package com.ocaterinca.ocaterinca.feature.gamecode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ocaterinca.ocaterinca.GameCodeFragmentBinding
import com.ocaterinca.ocaterinca.R

class GameCodeFragment : Fragment() {

    private lateinit var binding: GameCodeFragmentBinding
    private val viewModel = GameCodeViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_code, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@GameCodeFragment.viewModel
        }
    }
}