package com.nova.coroutinesample.user.repo

import androidx.lifecycle.LiveData
import com.nova.coroutinesample.user.model.User
import com.nova.coroutinesample.user.db.UserDao
import com.nova.coroutinesample.user.di.AppModule
import com.nova.coroutinesample.user.di.DaggerAppComponent
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