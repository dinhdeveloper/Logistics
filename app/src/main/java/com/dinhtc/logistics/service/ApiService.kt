package com.dinhtc.logistics.service

import com.dinhtc.logistics.model.BaseModel
import com.dinhtc.logistics.model.LoginModel
import com.dinhtc.logistics.utils.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("customer/list")
    suspend fun getUsers(): ApiResponse<LoginModel>
}