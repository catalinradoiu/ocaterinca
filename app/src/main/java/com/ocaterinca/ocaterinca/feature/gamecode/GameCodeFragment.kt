package com.ocaterinca.ocaterinca.feature.gamecode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ocaterinca.ocaterinca.GameCodeFragmentBinding
import com.ocaterinca.ocaterinca.GameViewModel
import com.ocaterinca.ocaterinca.R
import com.ocaterinca.ocaterinca.core.model.GameStep
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameCodeFragment : Fragment() {

    private lateinit var binding: GameCodeFragmentBinding
    private val gameCodeViewModel: GameCodeViewModel by viewModel()
    private val parentViewModel: GameViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_code, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        gameCodeViewModel.sharedViewModel = parentViewModel
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@GameCodeFragment.gameCodeViewModel
        }

        binding.nextButton.setOnClickListener {
            gameCodeViewModel.createOrJoinGroup()
        }

        gameCodeViewModel.hasJoinedGroup.observe(viewLifecycleOwner, Observer {
            parentViewModel.finishedStep.value = GameStep.CODE_ENTER
        })
    }
}