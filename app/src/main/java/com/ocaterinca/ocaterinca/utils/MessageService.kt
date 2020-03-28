package com.ocaterinca.ocaterinca.utils

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessageService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {

    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Prefs.token = token
    }


}