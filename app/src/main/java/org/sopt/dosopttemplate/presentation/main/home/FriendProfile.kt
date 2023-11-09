package org.sopt.dosopttemplate.presentation.main.home

import androidx.annotation.DrawableRes

data class FriendProfile(
    val id: Int,
    @DrawableRes val image: Int,
    val name: String,
    val message: String?,
)
