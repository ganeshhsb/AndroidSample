package com.nova.coroutinesample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainActivityModel : ViewModel() {
    val userList: MutableLiveData<User> = MutableLiveData()

    @Inject
    lateinit var userRepo: UserRepo

    init {
        DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
            .inject(this)
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