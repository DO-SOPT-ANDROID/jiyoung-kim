package org.sopt.dosopttemplate.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    val id: String,
    val pwd: String,
    val name: String = "",
    val mbti: String = "",
) : Parcelable
