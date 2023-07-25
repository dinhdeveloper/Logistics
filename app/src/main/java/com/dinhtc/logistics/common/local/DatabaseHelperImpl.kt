package com.dinhtc.logistics.common.local

import com.dinhtc.logistics.common.local.AppDatabase
import com.dinhtc.logistics.common.local.DatabaseHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.dinhtc.logistics.common.local.entity.User
import com.dinhtc.logistics.model.BaseModel
import com.dinhtc.logistics.model.LoginModel

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {
//    override fun getUsers(): Flow<List<User>> = flow {
//        emit(appDatabase.userDao().getAll())
//    }

    override fun getCustomer(): Flow<List<BaseModel<LoginModel>>> = flow {
        //emit()
    }
}