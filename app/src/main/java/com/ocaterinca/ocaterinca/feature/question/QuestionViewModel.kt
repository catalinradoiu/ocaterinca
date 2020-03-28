package com.ocaterinca.ocaterinca.feature.question

import android.os.Handler
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ocaterinca.ocaterinca.core.model.NewRoundPush
import com.ocaterinca.ocaterinca.core.model.Player
import com.ocaterinca.ocaterinca.core.model.RoundOverPush
import com.ocaterinca.ocaterinca.custom.choosePlayer.ChoosePlayerViewModel

class QuestionViewModel : ViewModel() {

    val choosePlayerViewModel = ChoosePlayerViewModel(this::votePlayer1, this::votePlayer2)
    val choosePlayerState = ObservableField<ChoosePlayerViewModel.State>()
    val questionText = ObservableField<String>()

    init {
        gotPush(
            NewRoundPush(
                "1. Cine e mai smecher?",
                "https://www.w3schools.com/w3images/avatar1.png",
                "https://www.w3schools.com/w3images/avatar2.png"
            )
        )
        Handler().postDelayed({
            gotPush(
                RoundOverPush(
                    "1. Cine e mai smecher raspunsuri",
                    "https://www.w3schools.com/w3images/avatar1.png",
                    "https://www.w3schools.com/w3images/avatar2.png",
                    false
                )
            )
        }, 3000)

        Handler().postDelayed({
            gotPush(
                RoundOverPush(
                    "1. Cine e mai smecher raspunsuri",
                    "https://www.w3schools.com/w3images/avatar1.png",
                    "https://www.w3schools.com/w3images/avatar2.png",
                    false
                )
            )
        }, 6000)

        Handler().postDelayed({
            gotPush(
                NewRoundPush(
                    "2. Cine e mai dejtept?",
                    "https://www.w3schools.com/w3images/avatar1.png",
                    "https://www.w3schools.com/w3images/avatar2.png"
                )
            )
        }, 6000)

    }

    private fun gotPush(push: Any?) {
        when (push) {
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

    private val _playersList = MutableLiveData<List<Player>>().apply {
        value = PLAYERS_MOCK_LIST
    }
    val playersList: LiveData<List<Player>> = _playersList

    companion object {
        private val PLAYERS_MOCK_LIST = listOf(
            Player("1", "https://www.w3schools.com/w3images/avatar1.png"),
            Player("2", "https://www.w3schools.com/w3images/avatar3.png"),
            Player("3", "https://www.w3schools.com/w3images/avatar2.png")
        )
    }

}