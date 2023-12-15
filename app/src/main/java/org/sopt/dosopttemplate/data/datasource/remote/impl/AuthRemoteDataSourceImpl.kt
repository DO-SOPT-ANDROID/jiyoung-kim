package org.sopt.dosopttemplate.data.datasource.remote.impl

import org.sopt.dosopttemplate.data.datasource.remote.AuthRemoteDataSource
import org.sopt.dosopttemplate.data.model.request.RequestSignInDto
import org.sopt.dosopttemplate.data.model.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.model.response.ResponseSignInDto
import org.sopt.dosopttemplate.data.service.AuthService
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService,
) : AuthRemoteDataSource {
    override suspend fun signUp(requestSignUpDto: RequestSignUpDto) =
        authService.signUp(requestSignUpDto)

    override suspend fun signIn(requestSignInDto: RequestSignInDto): ResponseSignInDto =
        authService.signIn(requestSignInDto)
}
