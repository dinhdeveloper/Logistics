package com.dinhtc.logistics.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinhtc.logistics.model.CustomerModel
import com.dinhtc.logistics.model.LoginModel
import com.dinhtc.logistics.service.ApiHelperImpl
import com.dinhtc.logistics.utils.UiState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class YourViewModel @Inject constructor(private val apiHelperImpl: ApiHelperImpl) : ViewModel() {

    private val _uiState = MutableLiveData<UiState<LoginModel>>()
    val uiState: LiveData<UiState<LoginModel>>
        get() = _uiState

    fun fetchDataFromApi() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val response = apiHelperImpl.getUsers()
                if (response.code_status == 200) {
                    Log.d("SSSSSSSS", Gson().toJson(response))
                    _uiState.value = UiState.Success(response)
                } else {
                    _uiState.value = UiState.Error("Error message")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error message: ${e.message}")
            }
        }
    }

    private val _dataCustomer = MutableLiveData<List<CustomerModel>>()
    val dataCustomer : LiveData<List<CustomerModel>>
        get() = _dataCustomer

    fun getListCustomer(){
        viewModelScope.launch {
            try {
                val response = apiHelperImpl.getListCustomer()
                _dataCustomer.value = response
            } catch (e: Exception) {
                Log.d("SSSSSSSSSSSS", e.message.toString())
            }
        }
    }


    private val _dataLogin = MutableLiveData<UiState<LoginModel>>()
    val dataLogin : LiveData<UiState<LoginModel>>
        get() = _dataLogin

    fun loginUser(userName: String, passWord: String) {
        viewModelScope.launch {
            _dataLogin.value = UiState.Loading
            try {
                val response = apiHelperImpl.loginUser(userName,passWord)
                if (response.code_status == 200) {
                    Log.d("SSSSSSSS", Gson().toJson(response))
                    _dataLogin.value = UiState.Success(response)
                } else {
                    _dataLogin.value = UiState.Error("Error message")
                }
            } catch (e: Exception) {
                _dataLogin.value = UiState.Error("Error message: ${e.message}")
            }
        }
    }


//    private var otpVerification_ = MutableLiveData<ResultState<ApiDataResponse<LoginModel>>>()
//    val otpVerification: LiveData<ResultState<ApiDataResponse<LoginModel>>> = otpVerification_
//
//    fun verifyOtpV1() {
////        apiHelperImpl.getUsers()
////            .doOnSubscribe { otpVerification_.value = ResultState.Loading(true) }
////            .doFinally { otpVerification_.value = ResultState.Loading(false) }
////            .subscribe { resultState ->
////                otpVerification_.value = resultState
////                //also reset livedata
////                otpVerification_.value = ResultState.Idle()
////            }.track()
//
//        otpVerification_.value = ResultState.Loading(true)
//        viewModelScope.launch {
//            try {
//                // Gọi API ở đây và lấy dữ liệu từ API
//                val data = apiHelperImpl.getUsers()
//                val response: ApiResponse<List<LoginModel>> = parseResponse(data.body().toString())
//                // Update trạng thái với dữ liệu thành công
//                otpVerification_.value = UiState.Success(response.data)
//            } catch (e: Exception) {
//                // Xử lý lỗi khi gọi API thất bại
//                otpVerification_.value = UiState.Error("Error message: ${e.message}")
//            }
//        }
//    }
}