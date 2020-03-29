package com.ocaterinca.ocaterinca.feature.question

import com.ocaterinca.ocaterinca.core.model.api.AnswerRequest
import com.ocaterinca.ocaterinca.core.model.api.RoomRequest
import com.ocaterinca.ocaterinca.feature.gamecode.GameCodeService
import com.ocaterinca.ocaterinca.utils.Prefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuestionsInteractor(private val gameCodeService: GameCodeService) {

    suspend fun startGame(userId: String?, code: String?) = withContext(Dispatchers.IO) {
        gameCodeService.startGame(RoomRequest(userId, code))
    }

    suspend fun answer(player1Won: Boolean?) = withContext(Dispatchers.IO) {
        gameCodeService.answer(AnswerRequest(Prefs.userId, Prefs.roomId, player1Won))
    }
}
