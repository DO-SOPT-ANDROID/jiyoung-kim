package org.sopt.dosopttemplate.data.datasource.local

import android.content.SharedPreferences
import androidx.core.content.edit
import org.sopt.dosopttemplate.domain.entity.User
import javax.inject.Inject

class AuthLocalDataSource @Inject constructor(
    private val pref: SharedPreferences,
) {
    private fun getString(key: String): String {
        return pref.getString(key, "default").toString()
    }

    private fun setString(key: String, value: String) =
        pref.edit {
            putString(key, value)
        }

    fun updateUser(user: User) {
        setString(USER_ID, user.id)
        setString(USER_PASSWORD, user.pwd)
        setString(USER_NAME, user.name ?: "")
        setString(USER_MBTI, user.mbti ?: "")
        setString(USER_BIRTHDAY, user.birthday ?: "")
    }

    fun getUser(): User {
        val id = getString(USER_ID)
        val pwd = getString(USER_PASSWORD)
        val name = getString(USER_NAME)
        val mbti = getString(USER_MBTI)
        val birthday = getString(USER_BIRTHDAY)

        return User(id, pwd, name, mbti, birthday)
    }

    fun deleteUser() = pref.edit { clear() }

    var isAlreadyExistUserInfo: Boolean
        set(value) = pref.edit { putBoolean(IS_ALREADY_EXIST_USER, value) }
        get() = pref.getBoolean(IS_ALREADY_EXIST_USER, false)

    companion object {
        private const val USER_ID = "userId"
        private const val USER_PASSWORD = "userPassword"
        private const val USER_NAME = "userName"
        private const val USER_MBTI = "userMbti"
        private const val IS_ALREADY_EXIST_USER = "isAlreadyExistUser"
        private const val USER_BIRTHDAY = "userBirthday"
    }
}
