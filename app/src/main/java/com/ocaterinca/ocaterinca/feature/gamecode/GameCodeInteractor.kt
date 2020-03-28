package com.ocaterinca.ocaterinca.feature.gamecode

import com.ocaterinca.ocaterinca.core.model.api.CodeUploadRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GameCodeInteractor(private val gameCodeService: GameCodeService) {

    suspend fun uploadGameCode(userId: String, code: String) = withContext(Dispatchers.IO) {
        gameCodeService.createOrJoinGroup(CodeUploadRequest(userId, code))
    }
}