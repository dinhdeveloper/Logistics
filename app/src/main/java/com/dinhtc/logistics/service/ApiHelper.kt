package com.dinhtc.logistics.service

import com.dinhtc.logistics.model.LoginModel
import com.dinhtc.logistics.utils.ApiResponse

interface ApiHelper {
    suspend fun getUsers(): ApiResponse<LoginModel>
}