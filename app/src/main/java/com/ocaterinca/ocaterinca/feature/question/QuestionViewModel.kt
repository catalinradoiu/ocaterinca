package com.ocaterinca.ocaterinca.feature.question

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ocaterinca.ocaterinca.R
import com.ocaterinca.ocaterinca.core.model.NewRoundPush
import com.ocaterinca.ocaterinca.core.model.Player
import com.ocaterinca.ocaterinca.core.model.RefreshUsersPush
import com.ocaterinca.ocaterinca.core.model.RoundOverPush
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

    private val _playersList = MutableLiveData<List<Player>>()
    val playersList: LiveData<List<Player>> = _playersList


    fun start(initialPlayers: MutableList<Player>?) {
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
                choosePlayerState.set(ChoosePlayerViewModel.State.PickPlayer)
                questionText.set(push.title)
                choosePlayerViewModel.newRound(push)
            }
            is RoundOverPush -> {
                val state = when (push.player1Won) {
                    true -> ChoosePlayerViewModel.State.Player1Won
                    false -> ChoosePlayerViewModel.State.Player2Won
                    null -> ChoosePlayerViewModel.State.Draft
                }
                questionText.set(push.title)
                choosePlayerState.set(state)
                choosePlayerViewModel.roundOver(push)
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