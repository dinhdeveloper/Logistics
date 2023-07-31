package com.dinhtc.logistics.service

import android.util.Log
import com.dinhtc.logistics.model.CustomerModel
import com.dinhtc.logistics.model.LoginModel
import com.dinhtc.logistics.model.LoginRequest
import com.dinhtc.logistics.model.TaskCustomerModel
import com.dinhtc.logistics.model.model_request.TaskCustomerRequest
import com.dinhtc.logistics.utils.ApiResponse
import com.google.gson.Gson
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

    override suspend fun getTaskByCustomer(
        userTask: String,
        taskStatus: String?,
        timeTask: String,
        statusUser: String
    ): ApiResponse<TaskCustomerModel> {
        val taskRequest = TaskCustomerRequest(userTask, taskStatus, timeTask, statusUser)
        Log.e("SSSSSSSSSSSS", Gson().toJson(taskRequest))
        return apiService.getTaskByCustomer(taskRequest)
    }

}