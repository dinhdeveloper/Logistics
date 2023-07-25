package com.dinhtc.logistics.common.local

import kotlinx.coroutines.flow.Flow
import com.dinhtc.logistics.common.local.entity.User
import com.dinhtc.logistics.model.BaseModel
import com.dinhtc.logistics.model.LoginModel

interface DatabaseHelper {
    fun getCustomer(): Flow<List<BaseModel<LoginModel>>>
}