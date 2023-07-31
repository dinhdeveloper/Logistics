package com.dinhtc.logistics.model

data class TaskCustomerModel(
    val id: Int,
    val taskName: String,
    val groupUser: String,
    val taskStatus: String,
    val timeTask: String,
    val userTask: String
)