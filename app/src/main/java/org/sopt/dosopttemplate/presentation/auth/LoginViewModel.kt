package org.sopt.dosopttemplate.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    private fun checkLoginValid(loginInfo: User, signUpInfo: User): Boolean =
        (loginInfo.id == signUpInfo.id && loginInfo.pwd == signUpInfo.pwd)

    fun saveUser(saveUser: User) {
        this.user = saveUser
    }

    fun login(user: User) {
        viewModelScope.launch {
            runCatching {
                authRepository.signIn(user.id, user.pwd)
            }.onSuccess {
                _loginState.value = UiState.Success
            }.onFailure {
                _loginState.value = UiState.Failure(LOGIN_ERROR)
            }
        }
    }

    companion object {
        const val LOGIN_ERROR = "loginError"
    }
}
