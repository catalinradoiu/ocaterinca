package com.ocaterinca.ocaterinca.utils

import com.orhanobut.hawk.Hawk

object Prefs {
    private const val KEY_USER_ID = "user"
    private const val KEY_FIREBASE_TOKEN = "token"
    private const val KEY_IS_ADMIN = "isAdmin"
    var userId: String?
        get() = Hawk.get(KEY_USER_ID)
        set(value) {
            Hawk.put(KEY_USER_ID, value)
        }
    var token: String?
        get() = Hawk.get(KEY_FIREBASE_TOKEN)
        set(value) {
            Hawk.put(KEY_FIREBASE_TOKEN, value)
        }
    var isAdmin: Boolean?
        get() = Hawk.get(KEY_IS_ADMIN)
        set(value) {
            Hawk.put(KEY_IS_ADMIN, value)
        }
}
