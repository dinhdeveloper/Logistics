package com.dinhtc.logistics.service

import com.dinhtc.logistics.model.LoginModel
import com.dinhtc.logistics.utils.ApiResponse
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getUsers(): ApiResponse<LoginModel> {
        return apiService.getUsers()
    }

}