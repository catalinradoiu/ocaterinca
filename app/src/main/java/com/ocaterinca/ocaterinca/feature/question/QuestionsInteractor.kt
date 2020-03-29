package com.ocaterinca.ocaterinca.feature.question

import com.ocaterinca.ocaterinca.core.model.api.RoomRequest
import com.ocaterinca.ocaterinca.feature.gamecode.GameCodeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuestionsInteractor(private val gameCodeService: GameCodeService) {

    suspend fun startGame(userId: String?, code: String?) = withContext(Dispatchers.IO) {
        gameCodeService.startGame(RoomRequest(userId, code))
    }
}
