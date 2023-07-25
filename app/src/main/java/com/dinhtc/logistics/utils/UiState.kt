package com.dinhtc.logistics.utils

sealed interface UiState<out T> {

    data class Success<T>(val data: ApiResponse<T>) : UiState<T>

    data class Error(val message: String) : UiState<Nothing>

    object Loading : UiState<Nothing>

}