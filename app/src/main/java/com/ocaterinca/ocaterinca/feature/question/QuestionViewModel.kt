package com.ocaterinca.ocaterinca.feature.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestionViewModel : ViewModel() {

    private val _questionText = MutableLiveData<String>()
    val questionText: LiveData<String> = _questionText

    private val _firstPlayerAvatarUrl = MutableLiveData<String>()
    val firstPlayerAvatarUrl : LiveData<String> = _firstPlayerAvatarUrl

    private val _secondPlayerAvatarUrl = MutableLiveData<String>()
    val secondPlayerAvatarUrl: LiveData<String> = _secondPlayerAvatarUrl

}