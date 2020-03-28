package com.ocaterinca.ocaterinca.feature

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import com.dtl.kaloric.refactoring.utils.ImageUtils
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.File

class AvatarCardViewModel {

    val imagePath = ObservableField<String>()
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
                Log.e("AvatarCardModel", "Eroare", e)
            }
        }
    }

    private fun imageWasResized(resizedFile: File) {
        imagePath.set(resizedFile.absolutePath)
        nextEnabled.set(true)
    }

    val nextEnabled = ObservableBoolean(false)
    val nextButtonAlpha: ObservableFloat = object : ObservableFloat(nextEnabled) {
        override fun get() = if (nextEnabled.get()) 1.0f else .5f
    }
}