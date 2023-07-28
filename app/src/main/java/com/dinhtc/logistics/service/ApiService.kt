package com.dinhtc.logistics.service

import com.dinhtc.logistics.model.CustomerModel
import com.dinhtc.logistics.model.LoginModel
import com.dinhtc.logistics.model.LoginRequest
import com.dinhtc.logistics.utils.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("customer/list")
    suspend fun getUsers(): ApiResponse<LoginModel>

    @GET("users")
    suspend fun getCustomer(): List<CustomerModel>

    @POST("customer/login")
    suspend fun loginUser(@Body loginRequest : LoginRequest): ApiResponse<LoginModel>
}