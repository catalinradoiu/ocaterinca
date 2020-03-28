package com.ocaterinca.ocaterinca.feature

import com.ocaterinca.ocaterinca.core.model.api.AvatarUploadBody
import com.ocaterinca.ocaterinca.core.model.api.AvatarUploadResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AvatarService {

    @POST("users")
    suspend fun uploadImage(@Body avatarUploadBody: AvatarUploadBody): AvatarUploadResponse

}