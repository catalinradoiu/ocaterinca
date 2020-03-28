package com.ocaterinca.ocaterinca

import androidx.lifecycle.ViewModel
import com.ocaterinca.ocaterinca.utils.LiveEvent

class GameViewModel : ViewModel() {

    val gameStarted = LiveEvent<Boolean>()
}