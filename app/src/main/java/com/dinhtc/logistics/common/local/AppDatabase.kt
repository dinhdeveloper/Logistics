package com.dinhtc.logistics.common.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dinhtc.logistics.common.local.dao.UserDao
import com.dinhtc.logistics.common.local.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}