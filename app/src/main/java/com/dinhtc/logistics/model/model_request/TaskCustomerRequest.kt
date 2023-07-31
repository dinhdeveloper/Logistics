package com.dinhtc.logistics.model.model_request

data class TaskCustomerRequest(
    val userTask: String,
    val taskStatus: String?,
    val timeTask: String,
    val statusUser : String
)