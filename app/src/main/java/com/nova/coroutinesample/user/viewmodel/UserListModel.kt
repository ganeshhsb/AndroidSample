package com.nova.coroutinesample.user.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nova.coroutinesample.common.SingleLiveEvent
import com.nova.coroutinesample.user.model.User
import com.nova.coroutinesample.user.repo.UserRepo
import org.koin.core.KoinComponent
import org.koin.core.inject

class UserListModel : ViewModel(), KoinComponent {
    val userList: MutableLiveData<User> = MutableLiveData()
    val singleEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()

    private val userRepo: UserRepo by inject()

    fun getUsers(): LiveData<MutableList<User>> {

        return userRepo.getUser()
    }

    fun insert(user: User) {
        userRepo.insert(user)
    }

    fun delete(user: User) {
        userRepo.delete(user)
    }

    fun update(user: User) {
        userRepo.update(user)
    }
}