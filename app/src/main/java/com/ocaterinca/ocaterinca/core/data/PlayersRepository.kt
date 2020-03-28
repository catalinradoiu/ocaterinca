package com.ocaterinca.ocaterinca.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ocaterinca.ocaterinca.core.model.Player

class PlayersRepository {

    private val _playersList = MutableLiveData<List<Player>>()
    val playersList: LiveData<List<Player>> = _playersList

    fun savePlayers(playersList: List<Player>) {
        _playersList.postValue(playersList)
    }
}