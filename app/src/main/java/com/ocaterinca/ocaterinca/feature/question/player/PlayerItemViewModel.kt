package com.ocaterinca.ocaterinca.feature.question.player

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField

class PlayerItemViewModel {

    val avatarUrl = ObservableField<String>()
    val playerAnswered = ObservableBoolean(false)
}