package org.sopt.dosopttemplate.presentation.main.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.dosopttemplate.domain.entity.User
import org.sopt.dosopttemplate.domain.repository.AuthRepository
import org.sopt.dosopttemplate.util.UiState
import javax.inject.Inject

@HiltViewModel
class MypageViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {
    val user = MutableLiveData<User>()
    private val _withdrawState = MutableLiveData<UiState>()
    val withdrawState: LiveData<UiState> get() = _withdrawState

    init {
        user.value = getUserInfo()
    }

    private fun getUserInfo(): User = authRepository.getUser() ?: User("", "", "", "")

    fun deleteUser() {
        runCatching {
            authRepository.deleteUser()
        }.onSuccess {
            _withdrawState.value = UiState.Success
        }.onFailure {
            _withdrawState.value = UiState.Failure(null)
        }
    }
}
