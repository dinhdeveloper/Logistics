package com.dinhtc.logistics.common.api

import com.dinhtc.logistics.common.local.entity.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>
//
//    @GET("more-users")
//    suspend fun getMoreUsers(): List<ApiUser>
//
//    @GET("error")
//    suspend fun getUsersWithError(): List<ApiUser>

}