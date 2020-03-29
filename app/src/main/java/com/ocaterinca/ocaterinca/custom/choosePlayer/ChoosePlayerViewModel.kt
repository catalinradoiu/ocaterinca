package com.ocaterinca.ocaterinca.custom.choosePlayer

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.ocaterinca.ocaterinca.core.model.NewRoundPush
import com.ocaterinca.ocaterinca.core.model.RoundOverPush

class ChoosePlayerViewModel(val votePlayer1: () -> Unit, val votePlayer2: () -> Unit) {

    val player1Image = ObservableField<String>()
    val player2Image = ObservableField<String>()
    val player1Selected = ObservableBoolean(false)
    val player2Selected = ObservableBoolean(false)

    private var roundOver = false

    fun newRound(push: NewRoundPush) {
        roundOver = false
        player1Image.set(push.player1Image)
        player2Image.set(push.player2Image)
        player1Selected.set(false)
        player2Selected.set(false)
    }

    fun roundOver(push: RoundOverPush) {
        player1Image.set(push.player1Image)
        player2Image.set(push.player2Image)
        roundOver = true
        player1Selected.set(false)
        player2Selected.set(false)
    }

    fun player1Tap() {
        if (roundOver || player1Selected.get() || player2Selected.get()) {
            return
        }
        votePlayer1()
        player1Selected.set(true)
        player2Selected.set(false)
    }

    fun player2Tap() {
        if (roundOver || player1Selected.get() || player2Selected.get()) {
            return
        }
        votePlayer2()
        player1Selected.set(false)
        player2Selected.set(true)
    }


    enum class State {
        PickPlayer, Player1Won, Player2Won, Draft
    }
}