package com.ocaterinca.ocaterinca.core.model.api

import com.squareup.moshi.Json

data class CodeUploadRequest(
    @Json(name = "userId")
    val userId: String,

    @Json(name = "roomId")
    val roomId: String
)