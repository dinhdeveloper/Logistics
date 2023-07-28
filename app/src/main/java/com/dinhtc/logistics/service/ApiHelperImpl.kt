package com.dinhtc.logistics.service

import com.dinhtc.logistics.model.CustomerModel
import com.dinhtc.logistics.model.LoginModel
import com.dinhtc.logistics.model.LoginRequest
import com.dinhtc.logistics.utils.ApiResponse
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getUsers(): ApiResponse<LoginModel> {
        return apiService.getUsers()
    }

    override suspend fun getListCustomer(): List<CustomerModel> {
        return apiService.getCustomer()
    }

    override suspend fun loginUser(userName: String, passWord: String): ApiResponse<LoginModel> {
        val loginRequest = LoginRequest(userName = userName, passWord = passWord)
        return apiService.loginUser(loginRequest)
    }

}