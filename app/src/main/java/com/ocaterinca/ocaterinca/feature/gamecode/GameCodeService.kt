package com.ocaterinca.ocaterinca.feature.gamecode

import com.ocaterinca.ocaterinca.core.model.api.CodeUploadReponse
import com.ocaterinca.ocaterinca.core.model.api.CodeUploadRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface GameCodeService {

    @POST("createOrJoin")
    suspend fun createOrJoinGroup(@Body codeUploadRequest: CodeUploadRequest): CodeUploadReponse
}