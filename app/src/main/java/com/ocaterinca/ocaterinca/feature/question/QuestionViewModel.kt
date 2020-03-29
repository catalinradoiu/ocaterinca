package com.ocaterinca.ocaterinca.feature.question

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ocaterinca.ocaterinca.R
import com.ocaterinca.ocaterinca.core.model.*
import com.ocaterinca.ocaterinca.custom.choosePlayer.ChoosePlayerViewModel
import com.ocaterinca.ocaterinca.utils.Prefs
import com.ocaterinca.ocaterinca.utils.grabString

class QuestionViewModel(questionsInteractor: QuestionsInteractor) : ViewModel() {

    val choosePlayerViewModel = ChoosePlayerViewModel(this::votePlayer1, this::votePlayer2)
    val choosePlayerState = ObservableField<ChoosePlayerViewModel.State>()
    val questionText = ObservableField<String>()

    val showNext = ObservableBoolean(false)
    val showRestart = ObservableBoolean(false)
    val nextText = ObservableField<String>()
    val showChoosePlayers = ObservableBoolean(false)

    private val _playersList = MutableLiveData<List<Player>>()
    val playersList: LiveData<List<Player>> = _playersList


    fun start(initialPlayers: MutableList<Player>?) {
        showChoosePlayers.set(false)
        nextText.set(grabString(R.string.start))
        showNext.set(Prefs.isAdmin == true)
        showRestart.set(false)
        initialPlayers?.let { players ->
            _playersList.value = players
        }
    }

    fun gotPush(push: Any?) {
        showRestart.set(Prefs.isAdmin == true)
        nextText.set(grabString(R.string.next))
        when (push) {
            is RefreshUsersPush -> {
                _playersList.value = push.players
            }
            is NewRoundPush -> {
                showChoosePlayers.set(true)
                choosePlayerState.set(ChoosePlayerViewModel.State.PickPlayer)
                questionText.set(push.title)
                choosePlayerViewModel.newRound(push)
            }
            is RoundOverPush -> {
                showChoosePlayers.set(true)
                val state = when (push.player1Won) {
                    true -> ChoosePlayerViewModel.State.Player1Won
                    false -> ChoosePlayerViewModel.State.Player2Won
                    null -> ChoosePlayerViewModel.State.Draft
                }
                questionText.set(push.title)
                choosePlayerState.set(state)
                choosePlayerViewModel.roundOver(push)
            }
            is GameOverPush -> {
                showChoosePlayers.set(false)
            }
            is GameRestartPush -> {
                showChoosePlayers.set(false)
            }
        }
    }

    private fun votePlayer1() {
        // backend request
    }

    private fun votePlayer2() {
        // backend request
    }
}