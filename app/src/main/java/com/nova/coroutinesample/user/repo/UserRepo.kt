package com.nova.coroutinesample.user.repo

import androidx.lifecycle.LiveData
import com.nova.coroutinesample.user.db.UserDao
import com.nova.coroutinesample.user.model.User
import org.koin.core.KoinComponent
import org.koin.core.inject

class UserRepo : KoinComponent {

    val userDao: UserDao by inject()

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