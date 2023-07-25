package com.dinhtc.logistics.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinhtc.logistics.common.local.DatabaseHelper
import com.dinhtc.logistics.common.local.entity.User
import com.dinhtc.logistics.model.BaseModel
import com.dinhtc.logistics.model.LoginModel
import com.dinhtc.logistics.utils.DispatcherProvider
import com.dinhtc.logistics.utils.UiState
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class UserViewModel(
    //private val apiHelper: ApiHelper,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val _uiState = MutableLiveData<UiState<BaseModel<LoginModel>>>()
    val uiState: LiveData<UiState<BaseModel<LoginModel>>> get() = _uiState

    fun fetchDataFromApi() {
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            try {
                // Gọi API ở đây và lấy dữ liệu từ API
                //val data = apiHelper.getCustomer()
                    // Update trạng thái với dữ liệu thành công
                   // _uiState.value = UiState.Success(data.body())
            } catch (e: Exception) {
                // Xử lý lỗi khi gọi API thất bại
                _uiState.value = UiState.Error("Error message")
            }
        }
    }

}