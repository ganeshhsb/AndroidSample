package com.nova.coroutinesample

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}