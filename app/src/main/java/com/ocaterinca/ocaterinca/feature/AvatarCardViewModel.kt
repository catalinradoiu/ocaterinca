package com.ocaterinca.ocaterinca.feature

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableFloat

class AvatarCardViewModel {

    val nextEnabled = ObservableBoolean(false)
    val nextButtonAlpha: ObservableFloat = object : ObservableFloat(nextEnabled) {
        override fun get() = if (nextEnabled.get()) 1.0f else 0f
    }
}