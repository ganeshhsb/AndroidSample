package com.nova.coroutinesample.user.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nova.coroutinesample.user.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUsers():LiveData<MutableList<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Delete
    fun delete(user: User)

    @Update
    fun update(user: User)

}