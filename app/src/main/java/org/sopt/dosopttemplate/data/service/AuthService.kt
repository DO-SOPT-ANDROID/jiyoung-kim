package org.sopt.dosopttemplate.data.service

import org.sopt.dosopttemplate.data.model.request.RequestSignInDto
import org.sopt.dosopttemplate.data.model.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.model.response.ResponseGetUserDto
import org.sopt.dosopttemplate.data.model.response.ResponseSignInDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {
    @POST("api/v1/members")
    suspend fun signUp(
        @Body requestSignUpDto: RequestSignUpDto,
    )

    @POST("api/v1/members/sign-in")
    suspend fun signIn(
        @Body requestSignInDto: RequestSignInDto,
    ): ResponseSignInDto

    @GET("api/v1/members/{memberId}")
    suspend fun getUser(
        @Path("memberId") memberId: Int,
    ): ResponseGetUserDto
}
