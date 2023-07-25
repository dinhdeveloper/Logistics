package com.dinhtc.logistics.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dinhtc.logistics.common.local.DatabaseHelper
import com.dinhtc.logistics.viewmodel.UserViewModel

class ViewModelFactory(
    //private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper,
    private val dispatcherProvider: DispatcherProvider
) :
    ViewModelProvider.NewInstanceFactory() {

//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
//            return UserViewModel("apiHelper", dispatcherProvider) as T
//        }
//        throw IllegalArgumentException("Unknown class name")
//    }

}