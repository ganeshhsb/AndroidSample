package com.nova.coroutinesample

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nova.coroutinesample.user.db.UserDao
import com.nova.coroutinesample.user.model.User

@Database(entities = [User::class], version = 2,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}