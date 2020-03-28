package com.ocaterinca.ocaterinca.utils

import com.orhanobut.hawk.Hawk


object Prefs {
    private const val KEY_USER_ID = "user"
    var userId: String?
        get() = Hawk.get(KEY_USER_ID)
        set(value) {
            Hawk.put(KEY_USER_ID, value)
        }
}