package com.dinhtc.logistics.service

import com.dinhtc.logistics.model.CustomerModel
import com.dinhtc.logistics.model.LoginModel
import com.dinhtc.logistics.utils.ApiResponse

interface ApiHelper {
    suspend fun getUsers(): ApiResponse<LoginModel>
    suspend fun getListCustomer() : List<CustomerModel>
    suspend fun loginUser(userName: String, passWord: String): ApiResponse<LoginModel>
}