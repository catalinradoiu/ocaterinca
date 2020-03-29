package com.ocaterinca.ocaterinca.feature.question

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ocaterinca.ocaterinca.R
import com.ocaterinca.ocaterinca.core.model.*
import com.ocaterinca.ocaterinca.custom.choosePlayer.ChoosePlayerViewModel
import com.ocaterinca.ocaterinca.utils.Prefs
import com.ocaterinca.ocaterinca.utils.grabString
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber

class QuestionViewModel(private val questionsInteractor: QuestionsInteractor) : ViewModel() {

    val choosePlayerViewModel = ChoosePlayerViewModel(this::votePlayer1, this::votePlayer2)
    val choosePlayerState = ObservableField<ChoosePlayerViewModel.State>()
    val questionText = ObservableField<String>()

    val showNext = ObservableBoolean(false)
    val showRestart = ObservableBoolean(false)
    val nextText = ObservableField<String>()
    val showChoosePlayers = ObservableBoolean(false)
    val loading = ObservableBoolean(false)

    var firstRoundStarted = false

    private val _playersList = MutableLiveData<List<Player>>()
    val playersList: LiveData<List<Player>> = _playersList

    fun start(initialPlayers: MutableList<Player>?) {
        firstRoundStarted = false
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
        when (push) {
            is RefreshUsersPush -> {
                _playersList.value = push.players
            }
            is NewRoundPush -> {
                firstRoundStarted = true
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
                showNext.set(false)
                firstRoundStarted = false
                showChoosePlayers.set(false)
            }
            is GameRestartPush -> {
                showNext.set(Prefs.isAdmin == true)
                nextText.set(grabString(R.string.start))
                firstRoundStarted = false
                showChoosePlayers.set(false)
            }
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        loading.set(false)
        Timber.e(throwable)
    }

    fun nextClick() {
        loading.set(true)
        viewModelScope.launch(exceptionHandler) {
            questionsInteractor.startGame(Prefs.userId, Prefs.roomId)
            loading.set(false)
        }
    }

    fun restartClick() {

    }

    private fun votePlayer1() {
        // backend request
    }

    private fun votePlayer2() {
        // backend request
    }
}