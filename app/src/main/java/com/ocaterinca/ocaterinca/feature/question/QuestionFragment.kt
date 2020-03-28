package com.ocaterinca.ocaterinca.feature.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.ocaterinca.ocaterinca.GameViewModel
import com.ocaterinca.ocaterinca.QuestionFragmentBinding
import com.ocaterinca.ocaterinca.R
import com.ocaterinca.ocaterinca.feature.question.player.PlayersAdapter
import com.ocaterinca.ocaterinca.utils.AutoClearedValue
import com.ocaterinca.ocaterinca.utils.MessageService
import org.jetbrains.anko.support.v4.toast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class QuestionFragment : Fragment() {

    private lateinit var binding: QuestionFragmentBinding
    private val playersAdapter = PlayersAdapter()
    private val viewModel = QuestionViewModel()

    private var layoutManager by AutoClearedValue<GridLayoutManager>()

    private val gameViewModel: GameViewModel by sharedViewModel()
    private var lastItemsCount = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutManager = GridLayoutManager(context, 0)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@QuestionFragment.viewModel
            binding.playersList.apply {
                adapter = playersAdapter
                layoutManager = this@QuestionFragment.layoutManager
            }
        }
        initObservers()
    }

    private fun initObservers() {
        viewModel.playersList.observe(viewLifecycleOwner, Observer {
            playersAdapter.submitList(it)
            if (lastItemsCount != it.size) {
                layoutManager.spanCount = it.size
                lastItemsCount = it.size
            }
        })

        MessageService.messageReceiver.observe(viewLifecycleOwner, Observer {
            viewModel.gotPush(it)
            toast("Got new message! ${it.javaClass} ${Gson().toJson(it)}")
        })
        gameViewModel.gameStarted.observe(viewLifecycleOwner, Observer {
            if (it) {
                viewModel.start()
            }
        })
    }

}