package com.ocaterinca.ocaterinca.feature.gamecode

import com.ocaterinca.ocaterinca.core.model.api.AnswerRequest
import com.ocaterinca.ocaterinca.core.model.api.CodeUploadReponse
import com.ocaterinca.ocaterinca.core.model.api.RoomRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface GameCodeService {

    @POST("createOrJoin")
    suspend fun createOrJoinGroup(@Body roomRequest: RoomRequest): CodeUploadReponse

    @POST("answer")
    suspend fun answer(@Body answerRequest: AnswerRequest)

    @POST("startGame")
    suspend fun startGame(@Body roomRequest: RoomRequest)
}