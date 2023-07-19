package com.dinhtc.logistics.common.local

import kotlinx.coroutines.flow.Flow
import com.dinhtc.logistics.common.local.entity.User

interface DatabaseHelper {

    fun getUsers(): Flow<List<User>>

    fun insertAll(users: List<User>): Flow<Unit>

}