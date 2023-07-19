package com.dinhtc.logistics.common.api

import com.dinhtc.logistics.common.local.entity.User
import kotlinx.coroutines.flow.Flow

interface ApiHelper {

    fun getUsers(): Flow<List<User>>
//
//    fun getMoreUsers(): Flow<List<ApiUser>>
//
//    fun getUsersWithError(): Flow<List<ApiUser>>

}