package com.ocaterinca.ocaterinca.feature

import com.ocaterinca.ocaterinca.core.model.api.AvatarUploadBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AvatarCardInteractor(private val avatarService: AvatarService) {

    suspend fun uploadImage(imageBase64: String, token: String) =
        withContext(Dispatchers.IO) {
            avatarService.uploadImage(AvatarUploadBody(token, imageBase64)).userId
        }
}