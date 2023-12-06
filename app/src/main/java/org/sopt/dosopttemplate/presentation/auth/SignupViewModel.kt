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
class SignupViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {
    private val _signUpState = MutableLiveData<UiState>()
    val signUpState: LiveData<UiState> get() = _signUpState

    private val _isSignUpValid = MutableLiveData<Boolean>()
    val isSignUpValid: LiveData<Boolean> get() = _isSignUpValid

    private fun checkIdValid(id: String): Boolean = id.length in 6..10 && id.isNotBlank()
    private fun checkPwdValid(pwd: String): Boolean =
        pwd.length in 8..12 && pwd.isNotBlank()

    private fun checkNameValid(name: String): Boolean = name.isNotEmpty()

    private fun checkMbtiValid(mbti: String): Boolean = mbti.isNotEmpty()

    fun signUp(user: User) {
        viewModelScope.launch {
            runCatching {
                authRepository.signUp(user.id, user.name, user.pwd)
            }.onSuccess {
                _signUpState.value = UiState.Success
            }.onFailure {
                _signUpState.value = UiState.Failure(ERROR)
            }
        }
    }

    companion object {
        const val ID_ERROR = "idError"
        const val PWD_ERROR = "pwdError"
        const val EMPTY_ERROR = "emptyError"
        const val ERROR = "Error"
    }
}
