package org.sopt.dosopttemplate.util

sealed class UiState {
    object Loading : UiState()
    data class Success<T>(val data: T) : UiState()
    data class Error(val error: Throwable?) : UiState()
}
