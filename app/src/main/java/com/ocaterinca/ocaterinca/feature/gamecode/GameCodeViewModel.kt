package com.ocaterinca.ocaterinca.feature.gamecode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ocaterinca.ocaterinca.utils.dependantLiveData

class GameCodeViewModel : ViewModel() {

    val gameCode = MutableLiveData<String>()

    val nextEnabled = dependantLiveData(gameCode) {
        gameCode.value?.isNotEmpty() ?: false
    }

    val nextButtonAlpha = dependantLiveData(nextEnabled) {
        if (nextEnabled.value == true) 1f else .5f
    }
}