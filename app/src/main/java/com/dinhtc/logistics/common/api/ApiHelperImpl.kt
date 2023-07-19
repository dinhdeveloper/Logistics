package com.dinhtc.logistics.common.api

import com.dinhtc.logistics.common.api.ApiHelper
import com.dinhtc.logistics.common.local.entity.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override fun getUsers() = flow { emit(apiService.getUsers()) }
//
//    override fun getMoreUsers() = flow { emit(apiService.getMoreUsers()) }
//
//    override fun getUsersWithError() = flow { emit(apiService.getUsersWithError()) }
}