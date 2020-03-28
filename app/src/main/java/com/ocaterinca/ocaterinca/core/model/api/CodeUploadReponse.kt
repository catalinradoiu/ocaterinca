package com.ocaterinca.ocaterinca.core.model.api

import com.squareup.moshi.Json

data class CodeUploadReponse(
    @Json(name = "isAdmin")
    val isAdmin: Boolean
)