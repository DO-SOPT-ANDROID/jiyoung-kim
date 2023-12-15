package org.sopt.dosopttemplate.data.model.response

import kotlinx.serialization.SerialName

data class ResponseSignInDto(
    @SerialName("id")
    val id: Int,
    @SerialName("username")
    val username: String,
    @SerialName("nickname")
    val nickname: String,
)
