package com.dinhtc.logistics.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinhtc.logistics.common.api.ApiHelper
import com.dinhtc.logistics.common.local.DatabaseHelper
import com.dinhtc.logistics.common.local.entity.User
import com.dinhtc.logistics.utils.DispatcherProvider
import com.dinhtc.logistics.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class UserViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<User>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<User>>> = _uiState

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch(dispatcherProvider.main) {
            _uiState.value = UiState.Loading
            dbHelper.getUsers()
                .flatMapConcat { usersFromDb ->
                    if (usersFromDb.isEmpty()) {
                        return@flatMapConcat apiHelper.getUsers()
                            .map { apiUserList ->
                                val userList = mutableListOf<User>()
                                for (apiUser in apiUserList) {
                                    val user = User(
                                        apiUser.id,
                                        apiUser.name,
                                        apiUser.email,
                                        apiUser.avatar
                                    )
                                    userList.add(user)
                                }
                                userList
                            }
                            .flatMapConcat { usersToInsertInDB ->
                                dbHelper.insertAll(usersToInsertInDB)
                                    .flatMapConcat {
                                        flow {
                                            emit(usersToInsertInDB)
                                        }
                                    }
                            }
                    } else {
                        return@flatMapConcat flow {
                            emit(usersFromDb)
                        }
                    }
                }
                .flowOn(dispatcherProvider.io)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }


}