package com.ocaterinca.ocaterinca.feature.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ocaterinca.ocaterinca.core.model.Player

class QuestionViewModel : ViewModel() {

    private val _questionText = MutableLiveData<String>("Cine e mai smecher?")
    val questionText: LiveData<String> = _questionText

    private val _firstPlayerAvatarUrl = MutableLiveData<String>("https://www.w3schools.com/w3images/avatar2.png")
    val firstPlayerAvatarUrl : LiveData<String> = _firstPlayerAvatarUrl

    private val _secondPlayerAvatarUrl = MutableLiveData<String>("https://www.w3schools.com/w3images/avatar2.png").apply {
        value = "https://www.w3schools.com/w3images/avatar2.png"
    }
    val secondPlayerAvatarUrl: LiveData<String> = _secondPlayerAvatarUrl

    private val _playersList = MutableLiveData<List<Player>>().apply {
        value = PLAYERS_MOCK_LIST
    }
    val playersList: LiveData<List<Player>> = _playersList

    companion object {
        private val PLAYERS_MOCK_LIST = listOf(
            Player("1", "https://www.w3schools.com/w3images/avatar2.png"),
            Player("2", "https://www.w3schools.com/w3images/avatar2.png"),
            Player("3", "https://www.w3schools.com/w3images/avatar2.png")
        )
    }

}