package org.sopt.dosopttemplate.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetUserDto(
    @SerialName("username")
    val username: String,
    @SerialName("nickname")
    val nickname: String,
)
