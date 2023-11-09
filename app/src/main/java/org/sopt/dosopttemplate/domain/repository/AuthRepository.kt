package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.domain.entity.User

interface AuthRepository {
    fun updateUser(user: User)
    fun getUser(): User?
    fun deleteUser()
    fun setAutoLogin(isAlreadyExist: Boolean)
    fun getAutoLogin(): Boolean
}
