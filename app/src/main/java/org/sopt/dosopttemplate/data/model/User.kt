package org.sopt.dosopttemplate.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.sopt.dosopttemplate.domain.entity.User

@Parcelize
data class User(
    val id: String,
    val pwd: String,
    val name: String?,
    val mbti: String?,
    val birthday: String?,
) : Parcelable {
    fun toUser(): User {
        return User(id, pwd, name ?: "", mbti ?: "", birthday ?: "")
    }
}
