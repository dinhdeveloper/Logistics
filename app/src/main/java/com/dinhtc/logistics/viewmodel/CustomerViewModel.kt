package com.dinhtc.logistics.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinhtc.logistics.model.TaskCustomerModel
import com.dinhtc.logistics.service.ApiHelperImpl
import com.dinhtc.logistics.utils.UiState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(private val apiHelperImpl: ApiHelperImpl) :
    ViewModel() {
    private val _uiState = MutableLiveData<UiState<TaskCustomerModel>>()
    val uiState: LiveData<UiState<TaskCustomerModel>>
        get() = _uiState

    fun getTaskByCustomer(
        userTask: String,
        taskStatus: String?,
        timeTask: String,
        statusUser: String
    ) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val response =
                    apiHelperImpl.getTaskByCustomer(userTask, taskStatus, timeTask, statusUser)
                if (response.code_status == 200) {
                    Log.d("SSSSSSSSSSS", Gson().toJson(response))
                    _uiState.value = UiState.Success(response)
                } else {
                    _uiState.value = UiState.Error("Error message")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error message: ${e.message}")
            }
        }
    }
}