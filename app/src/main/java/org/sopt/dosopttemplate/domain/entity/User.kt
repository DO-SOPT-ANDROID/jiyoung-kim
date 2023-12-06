package org.sopt.dosopttemplate.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String,
    val pwd: String,
    val name: String,
    val mbti: String?,
    val birthday: String?,
) : Parcelable {
    fun toUser() = User(
        id = id,
        pwd = pwd,
        name = name,
        mbti = mbti,
        birthday = birthday,
    )
}
