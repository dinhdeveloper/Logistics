package com.dinhtc.logistics.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class ApiResponse<T>(
    val code_status: Int,
    val data: List<T?>,
    val result_code: Int,
    val result_description: String,
    val timestamp: String
)

fun <T> parseResponse(responseString: String): ApiResponse<T> {
    // Sử dụng thư viện Gson để parse responseString thành ApiResponse
    // Giả sử bạn đã có Gson instance
    val gson = Gson()
    val typeToken = object : TypeToken<ApiResponse<T>>() {}.type
    return gson.fromJson(responseString, typeToken)
}

private suspend fun <T> fetchDataFromRemote(responseString: String): ApiResponse<T> = withContext(Dispatchers.IO) {
    val gson = Gson()
    //val userListType = object : TypeToken<T>() {}.type
    val typeToken = object : TypeToken<ApiResponse<T>>() {}.type
    gson.fromJson(responseString, typeToken)
}