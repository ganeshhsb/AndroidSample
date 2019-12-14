package com.nova.coroutinesample

import androidx.lifecycle.LiveData
import javax.inject.Inject

class UserRepo @Inject constructor() {

    @Inject
    lateinit var userDao: UserDao

    init {
        DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
            .inject(this)
    }

    fun getUser(): LiveData<MutableList<User>> {
        return userDao.getUsers()
    }

    fun insert(user: User) {
        userDao.insert(user)
    }

    fun delete(user: User) {
        userDao.delete(user)
    }

    fun update(user: User) {
        userDao.update(user)
    }
}