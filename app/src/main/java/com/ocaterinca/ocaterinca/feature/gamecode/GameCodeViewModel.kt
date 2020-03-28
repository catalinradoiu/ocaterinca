package com.ocaterinca.ocaterinca.feature.gamecode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ocaterinca.ocaterinca.utils.Prefs
import com.ocaterinca.ocaterinca.utils.dependantLiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber

class GameCodeViewModel(private val gameCodeInteractor: GameCodeInteractor) : ViewModel() {

    val gameCode = MutableLiveData<String>()

    val nextEnabled = dependantLiveData(gameCode) {
        gameCode.value?.isNotEmpty() ?: false
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _hasJoinedGroup = MutableLiveData<Boolean>()
    val hasJoinedGroup: LiveData<Boolean> = _hasJoinedGroup

    val nextButtonAlpha = dependantLiveData(nextEnabled, isLoading) {
        if (nextEnabled.value == true || isLoading.value == true) 1f else .5f
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _isLoading.value = false
        Timber.e(throwable)
    }

    fun createOrJoinGroup() {
        viewModelScope.launch(exceptionHandler) {
            _isLoading.value = true
            val response = gameCodeInteractor.uploadGameCode(Prefs.userId.orEmpty(), gameCode.value.orEmpty())
            Prefs.isAdmin = response.isAdmin
            _hasJoinedGroup.value = true
            Timber.e("Is admin : ${response.isAdmin}")
            _isLoading.value = false
        }
    }
}