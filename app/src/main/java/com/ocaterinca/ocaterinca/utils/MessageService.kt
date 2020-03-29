package com.ocaterinca.ocaterinca.utils

import androidx.lifecycle.MutableLiveData
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import com.ocaterinca.ocaterinca.core.model.*
import timber.log.Timber

class MessageService : FirebaseMessagingService() {

    companion object {
        private val gson = Gson()
        val messageReceiver = MutableLiveData<Any>()
    }

    override fun onMessageReceived(message: RemoteMessage) {
        try {
            Timber.e("Got push ${gson.toJson(message.data)}")
            val pushData = gson.fromJson(message.data.toString(), PushMessage::class.java)
            if (pushData.roomId != Prefs.roomId) {
                Timber.e("Receiving invalid push from room ${pushData.roomId}")
                return
            }
            var pushEvent: Any? = null
            when (pushData.event) {
                PushType.REFRESH_USERS -> {
                    pushEvent = gson.fromJson(pushData.data, RefreshUsersPush::class.java)
                }
                PushType.NEW_ROUND -> {
                    pushEvent = gson.fromJson(pushData.data, NewRoundPush::class.java)
                }
                PushType.ROUND_OVER -> {
                    pushEvent = gson.fromJson(pushData.data, RoundOverPush::class.java)
                }
                PushType.GAME_OVER -> {
                    pushEvent = gson.fromJson(pushData.data, GameOverPush::class.java)
                }
                PushType.GAME_RESTART -> {
                    pushEvent = GameRestartPush
                }
            }
            messageReceiver.postValue(pushEvent)
        } catch (e: Exception) {
            Timber.e(e, "error parsing message ${gson.toJson(message.data)}")
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Prefs.token = token
    }
}