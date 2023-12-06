package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.datasource.remote.AuthRemoteDataSource
import org.sopt.dosopttemplate.data.model.request.RequestSignInDto
import org.sopt.dosopttemplate.data.model.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.model.response.ResponseSignInDto
import org.sopt.dosopttemplate.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthRemoteDataSource,
) :
    AuthRepository {
    override suspend fun signUp(
        username: String,
        nickname: String,
        password: String,
    ): Result<Unit> = runCatching {
        authDataSource.signUp(
            RequestSignUpDto(
                username = username,
                nickname = nickname,
                password = password,
            ),
        )
    }

    override suspend fun signIn(
        username: String,
        password: String,
    ): Result<ResponseSignInDto> = kotlin.runCatching {
        authDataSource.signIn(RequestSignInDto(username, password))
    }
}
