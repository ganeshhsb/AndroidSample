package com.nova.coroutinesample

import androidx.lifecycle.LiveData
import androidx.room.*

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