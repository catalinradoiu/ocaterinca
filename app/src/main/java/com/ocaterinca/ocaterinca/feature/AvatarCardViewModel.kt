package com.ocaterinca.ocaterinca.feature

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ocaterinca.ocaterinca.utils.ImageUtils
import com.ocaterinca.ocaterinca.utils.Prefs
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File

class AvatarCardViewModel(private val avatarCardInteractor: AvatarCardInteractor) : ViewModel() {

    val imagePath = ObservableField<String>()
    val nextEnabled = ObservableBoolean(false)
    val nextButtonAlpha: ObservableFloat = object : ObservableFloat(nextEnabled) {
        override fun get() = if (nextEnabled.get()) 1.0f else .5f
    }

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private var imageBase64: String? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _isLoading.value = false
        Timber.e("Caught exception : $throwable")
    }

    fun pickedImage(file: File) {
        resizeImage(file.absolutePath)
    }

    fun uploadImage() {
        imageBase64?.let {
            viewModelScope.launch(exceptionHandler) {
                _isLoading.value = true
                Prefs.userId = avatarCardInteractor.uploadImage(it, Prefs.token.orEmpty())
                _isLoading.value = false
            }
        }
    }

    private fun resizeImage(absolutePath: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val resizedFile = ImageUtils.resizeImageKeepAspectRatio(absolutePath, 200, 200)
                withContext(Dispatchers.Main) {
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