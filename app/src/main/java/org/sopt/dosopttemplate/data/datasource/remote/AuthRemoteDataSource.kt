package org.sopt.dosopttemplate.data.datasource.remote

import org.sopt.dosopttemplate.data.model.request.RequestSignInDto
import org.sopt.dosopttemplate.data.model.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.model.response.ResponseSignInDto

interface AuthRemoteDataSource {
    suspend fun signUp(requestSignUpDto: RequestSignUpDto)

    suspend fun signIn(requestSignInDto: RequestSignInDto): ResponseSignInDto
}
