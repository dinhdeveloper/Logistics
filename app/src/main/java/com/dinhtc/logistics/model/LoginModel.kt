package com.dinhtc.logistics.model

data class LoginModel(
    val firstName: String,
    val id: Int,
    val lastLogin: String,
    val passWord: String,
    val userName: String
)