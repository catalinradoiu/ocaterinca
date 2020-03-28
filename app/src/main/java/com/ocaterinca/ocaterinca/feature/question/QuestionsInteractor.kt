package com.ocaterinca.ocaterinca.feature.question

import com.ocaterinca.ocaterinca.core.data.PlayersRepository

class QuestionsInteractor(private val playersRepository: PlayersRepository) {

    fun getPlayersLiveData() = playersRepository.playersList

}
