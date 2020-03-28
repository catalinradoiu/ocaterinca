package com.ocaterinca.ocaterinca.feature

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import com.ocaterinca.ocaterinca.utils.ImageUtils
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import timber.log.Timber
import java.io.File

class AvatarCardViewModel {
    private var imageBase64: String? = null
    val imagePath = ObservableField<String>()
    val nextEnabled = ObservableBoolean(false)
    val nextButtonAlpha: ObservableFloat = object : ObservableFloat(nextEnabled) {
        override fun get() = if (nextEnabled.get()) 1.0f else .5f
    }

    fun pickedImage(file: File) {
        resizeImage(file.absolutePath)
    }

    private fun resizeImage(absolutePath: String) {
        doAsync {
            try {
                val resizedFile = ImageUtils.resizeImageKeepAspectRatio(absolutePath, 200, 200)
                uiThread {
                    imageWasResized(resizedFile)
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    private fun imageWasResized(resizedFile: ImageUtils.Base64Image) {
        imagePath.set(resizedFile.file.absolutePath)
        imageBase64 = resizedFile.base64
        nextEnabled.set(true)
    }
}