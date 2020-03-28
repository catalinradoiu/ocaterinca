package com.ocaterinca.ocaterinca.core.model

import com.google.gson.JsonElement

class PushMessage(
    val event: PushType?,
    val roomId: String?,
    val data: JsonElement?
)
