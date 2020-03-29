package com.ocaterinca.ocaterinca

import androidx.lifecycle.ViewModel
import com.ocaterinca.ocaterinca.core.model.GameStep
import com.ocaterinca.ocaterinca.core.model.Player
import com.ocaterinca.ocaterinca.utils.LiveEvent

class GameViewModel : ViewModel() {

    val gameStarted = LiveEvent<Boolean>()
    var initialPlayers: MutableList<Player>? = null
    val finishedStep = LiveEvent<GameStep>()
}