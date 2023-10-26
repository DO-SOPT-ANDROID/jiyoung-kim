package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.datasource.local.AuthLocalDataSource
import org.sopt.dosopttemplate.domain.entity.User
import org.sopt.dosopttemplate.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val localAuthDataSource: AuthLocalDataSource) :
    AuthRepository {
    override fun updateUser(user: User) =
        localAuthDataSource.updateUser(user)

    override fun getUser(): User = localAuthDataSource.getUser()

    override fun deleteUser() = localAuthDataSource.deleteUser()
    override fun setAutoLogin(isAlreadyExist: Boolean) {
        localAuthDataSource.isAlreadyExistUserInfo = isAlreadyExist
    }

    override fun getAutoLogin(): Boolean = localAuthDataSource.isAlreadyExistUserInfo
}
