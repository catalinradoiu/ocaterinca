package com.ocaterinca.ocaterinca.feature

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import java.io.File

class AvatarCardViewModel {

    val imagePath = ObservableField<String>()
    fun pickedImage(file: File) {
        imagePath.set(file.absolutePath)
        nextEnabled.set(true)
    }

    val nextEnabled = ObservableBoolean(false)
    val nextButtonAlpha: ObservableFloat = object : ObservableFloat(nextEnabled) {
        override fun get() = if (nextEnabled.get()) 1.0f else .5f
    }
}