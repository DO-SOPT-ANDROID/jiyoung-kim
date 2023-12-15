package org.sopt.dosopttemplate.presentation.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.domain.repository.AuthRepository
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.extension.addSourceList
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {
    val id: MutableLiveData<String> = MutableLiveData("")
    val pwd: MutableLiveData<String> = MutableLiveData("")
    val name: MutableLiveData<String> = MutableLiveData()
    val mbti: MutableLiveData<String> = MutableLiveData()
    val birthday: MutableLiveData<String> = MutableLiveData()

    private val _signUpState = MutableLiveData<UiState>()
    val signUpState: LiveData<UiState> get() = _signUpState

    private val _isSignUpValid = MutableLiveData(false)
    val isSignUpValid: LiveData<Boolean> get() = _isSignUpValid

    private fun checkIdValid(id: String): Boolean = id.matches(ID_PATTERN) || id.isEmpty()
    private fun checkPwdValid(pwd: String): Boolean =
        pwd.matches(PWD_PATTERN) || pwd.isEmpty()

    val isIdValid: LiveData<Boolean> = id.map { id -> checkIdValid(id) }
    val isPwdValid: LiveData<Boolean> = pwd.map { pwd -> checkPwdValid(pwd) }

    init {
        checkIdValid(id.value.toString())
        checkPwdValid(pwd.value.toString())
    }

    private fun checkIsSignUpValid(): Boolean {
        _isSignUpValid.value =
            checkIdValid(id.value.toString()) && checkPwdValid(pwd.value.toString()) && !name.value.isNullOrEmpty() && !mbti.value.isNullOrEmpty() && !birthday.value.isNullOrEmpty()
        Log.e("signup", "_isSignUpValid.value:: ${_isSignUpValid.value}")
        return _isSignUpValid.value ?: false
    }

    val isEnabledSignUpBtn = MediatorLiveData<Boolean>().apply {
        addSourceList(id, pwd, name, mbti, birthday) { checkIsSignUpValid() }
    }

    fun signUp() {
        viewModelScope.launch {
            runCatching {
                authRepository.signUp(
                    id.value.toString(),
                    name.value.toString(),
                    pwd.value.toString(),
                )
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
        val ID_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,10}\$".toRegex()
        val PWD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[\\W_])[A-Za-z\\d\\W_]{6,12}\$".toRegex()
    }
}
