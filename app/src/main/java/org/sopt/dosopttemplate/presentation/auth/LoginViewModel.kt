package org.sopt.dosopttemplate.presentation.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.UserInfo
import org.sopt.dosopttemplate.util.UiState

class LoginViewModel : ViewModel() {
    private val _loginState = MutableLiveData<UiState>()
    val loginState: LiveData<UiState> get() = _loginState

    private val _isLoginValid = MutableLiveData<Boolean>()
    val isLoginValid: LiveData<Boolean> get() = _isLoginValid

    fun checkLoginValid(loginInfo: UserInfo, signUpInfo: UserInfo) {
        val valid = (loginInfo.id == signUpInfo.id && loginInfo.pwd == signUpInfo.pwd)
        _isLoginValid.value = valid
        Log.d("test", "_isLoginValid:: ${_isLoginValid.value?.toString()}")
    }
}
