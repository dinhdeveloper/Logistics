package com.dinhtc.logistics.model

data class BaseModel<T : Any?>(
    val code_status: Int,
    val data: T? = null,
    val result_code: Int,
    val result_description: String,
    val timestamp: String
)