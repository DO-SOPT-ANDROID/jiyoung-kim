package org.sopt.dosopttemplate.presentation.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.dosopttemplate.domain.entity.User
import org.sopt.dosopttemplate.domain.repository.AuthRepository
import org.sopt.dosopttemplate.util.UiState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {
    private val _loginState = MutableLiveData<UiState>()
    val loginState: LiveData<UiState> get() = _loginState

    private val _isLoginValid = MutableLiveData<Boolean>()
    val isLoginValid: LiveData<Boolean> get() = _isLoginValid

    private val _isAutoLoginValid = MutableLiveData(false)
    val isAutoLoginValid: LiveData<Boolean> get() = _isAutoLoginValid

    var user = User("", "", "", "", "")

    init {
        authRepository.getUser()
        setAutoLogin()
    }

    private fun checkLoginValid(loginInfo: User, signUpInfo: User): Boolean =
        (loginInfo.id == signUpInfo.id && loginInfo.pwd == signUpInfo.pwd)

    fun saveUser(saveUser: User) {
        this.user = saveUser
    }

    fun login(loginInfo: User, signUpInfo: User) {
        if (!checkLoginValid(loginInfo, signUpInfo)) {
            _loginState.value = UiState.Failure(LOGIN_ERROR)
        } else {
            _loginState.value = UiState.Success
            authRepository.setAutoLogin(true)
        }
    }

    private fun setAutoLogin() {
        if (authRepository.getAutoLogin() && authRepository.getUser() != null) {
            _loginState.value = UiState.Success
        } else {
            _loginState.value = UiState.Failure(null)
        }
        Log.d("login", "_loginState.value:: ${_loginState.value?.toString()}")
    }

    companion object {
        const val LOGIN_ERROR = "loginError"
    }
}
