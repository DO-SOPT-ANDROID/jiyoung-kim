package org.sopt.dosopttemplate.presentation.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.UserInfo
import org.sopt.dosopttemplate.util.UiState

class SignupViewModel : ViewModel() {
    private val _signUpState = MutableLiveData<UiState>()
    val signUpState: LiveData<UiState> get() = _signUpState

    private val _isSignUpValid = MutableLiveData<Boolean>()
    val isSignUpValid: LiveData<Boolean> get() = _isSignUpValid

    private fun checkIdValid(id: String): Boolean = id.length in 6..10 && id.isNotBlank()
    private fun checkPwdValid(pwd: String): Boolean =
        pwd.length in 8..12 && pwd.isNotBlank()

    private fun checkNameValid(name: String): Boolean = name.isNotEmpty()

    private fun checkMbtiValid(mbti: String): Boolean = mbti.isNotEmpty()

    fun checkSignUpValid(userInfo: UserInfo) {
        val isValid =
            checkIdValid(userInfo.id) && checkPwdValid(userInfo.pwd) && checkNameValid(userInfo.name) && checkMbtiValid(
                userInfo.mbti,
            )
        _isSignUpValid.value = isValid
        Log.d("test", "_isSignUpValid:: ${_isSignUpValid.value?.toString()}")
    }
}
