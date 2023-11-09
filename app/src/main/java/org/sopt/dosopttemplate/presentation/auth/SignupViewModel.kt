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
        if (!checkIdValid(user.id)) {
            _signUpState.value = UiState.Failure(ID_ERROR)
            _isSignUpValid.value = false
        }
        if (!checkPwdValid(user.pwd)) {
            _signUpState.value = UiState.Failure(PWD_ERROR)
            _isSignUpValid.value = false
        }
        if (!checkNameValid(user.pwd) || checkMbtiValid(user.pwd)) {
            _signUpState.value = UiState.Failure(EMPTY_ERROR)
            _isSignUpValid.value = false
        }
        _isSignUpValid.value = true
        _signUpState.value = UiState.Success
        authRepository.updateUser(user)
        Log.d("test", "_isSignUpValid:: ${_isSignUpValid.value?.toString()}")
    }

    companion object {
        const val ID_ERROR = "idError"
        const val PWD_ERROR = "pwdError"
        const val EMPTY_ERROR = "emptyError"
    }
}
