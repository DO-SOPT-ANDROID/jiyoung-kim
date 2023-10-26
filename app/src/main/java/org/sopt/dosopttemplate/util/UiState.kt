package org.sopt.dosopttemplate.util

sealed class UiState {
    object Success : UiState()
    data class Failure(val type: String?) : UiState()
}
