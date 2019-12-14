package com.nova.coroutinesample.user.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nova.coroutinesample.common.SingleLiveEvent
import com.nova.coroutinesample.user.di.AppModule
import com.nova.coroutinesample.user.di.DaggerAppComponent
import com.nova.coroutinesample.user.repo.UserRepo
import com.nova.coroutinesample.user.model.User
import javax.inject.Inject

class UserListModel : ViewModel() {
    val userList: MutableLiveData<User> = MutableLiveData()
    val singleEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()

    @Inject
    lateinit var userRepo: UserRepo

    init {
        DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
            .inject(this)
        singleEvent.postValue(true)
    }

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