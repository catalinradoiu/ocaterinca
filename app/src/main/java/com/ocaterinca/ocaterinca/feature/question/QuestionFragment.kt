package com.ocaterinca.ocaterinca.feature.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.ocaterinca.ocaterinca.GameViewModel
import com.ocaterinca.ocaterinca.QuestionFragmentBinding
import com.ocaterinca.ocaterinca.R
import com.ocaterinca.ocaterinca.feature.question.player.PlayersAdapter
import com.ocaterinca.ocaterinca.utils.MessageService
import org.jetbrains.anko.support.v4.toast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class QuestionFragment : Fragment() {

    private lateinit var binding: QuestionFragmentBinding
    private val playersAdapter = PlayersAdapter()
    private val viewModel = QuestionViewModel()

    private val gameViewModel: GameViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@QuestionFragment.viewModel
            binding.playersList.apply {
                adapter = playersAdapter
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            }
        }
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        toast("On rezum")
    }
    private fun initObservers() {
        viewModel.playersList.observe(viewLifecycleOwner, Observer {
            playersAdapter.submitList(it)
        })

        MessageService.messageReceiver.observe(viewLifecycleOwner, Observer {
            toast("Got new message! ${it.javaClass} ${Gson().toJson(it)}")
        })
        gameViewModel.gameStarted.observe(viewLifecycleOwner, Observer {
            if (it) {
                toast("Start game")
                viewModel.start()
            }
        })
    }

}