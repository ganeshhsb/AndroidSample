package com.nova.coroutinesample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nova.coroutinesample.user.db.UserDao
import com.nova.coroutinesample.user.model.User

@Database(entities = [User::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {


    companion object {

        private const val DATABASE_NAME = "AppDatabase.db"

        fun getInstance(context: Context): AppDatabase =
            synchronized(this) { buildDatabase(context) }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, DATABASE_NAME
            )
                .addMigrations(MIGRATION_1_2)
//                .fallbackToDestructiveMigrationFrom(1)
                .build()

    }

    abstract fun getUserDao(): UserDao
}