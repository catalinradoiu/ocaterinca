package com.ocaterinca.ocaterinca.feature.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ocaterinca.ocaterinca.core.model.Player

class QuestionViewModel : ViewModel() {

    private val _questionText = MutableLiveData<String>()
    val questionText: LiveData<String> = _questionText

    private val _firstPlayerAvatarUrl = MutableLiveData<String>()
    val firstPlayerAvatarUrl : LiveData<String> = _firstPlayerAvatarUrl

    private val _secondPlayerAvatarUrl = MutableLiveData<String>()
    val secondPlayerAvatarUrl: LiveData<String> = _secondPlayerAvatarUrl

    private val _playersList = MutableLiveData<List<Player>>().apply {
        value = PLAYERS_MOCK_LIST
    }
    val playersList: LiveData<List<Player>> = _playersList

    companion object {
        private val PLAYERS_MOCK_LIST = listOf(
            Player("1", "https://www.w3schools.com/w3images/avatar2.png",false),
            Player("1", "https://www.w3schools.com/w3images/avatar2.png",true),
            Player("1", "https://www.w3schools.com/w3images/avatar2.png",false)
        )
    }

}