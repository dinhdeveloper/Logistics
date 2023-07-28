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

data class ApiResponseNoList<T>(
    val code_status: Int,
    val data: T?,
    val result_code: Int,
    val result_description: String,
    val timestamp: String
)

