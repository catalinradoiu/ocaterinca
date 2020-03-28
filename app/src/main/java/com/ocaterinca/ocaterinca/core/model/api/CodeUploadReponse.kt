package com.ocaterinca.ocaterinca.core.model.api

import com.ocaterinca.ocaterinca.core.model.Player
import com.squareup.moshi.Json

data class CodeUploadReponse(
    @Json(name = "isAdmin")
    val isAdmin: Boolean?,
    @Json(name = "roomId")
    val roomId: String?,
    @Json(name = "players")
    val players: MutableList<Player>?
)