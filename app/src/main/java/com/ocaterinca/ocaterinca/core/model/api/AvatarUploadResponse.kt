package com.ocaterinca.ocaterinca.core.model.api

import com.squareup.moshi.Json

data class AvatarUploadResponse(
    @Json(name = "userId")
    val userId: String
)