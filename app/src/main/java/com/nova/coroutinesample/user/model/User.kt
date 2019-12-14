package com.nova.coroutinesample.user.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "address")
    var address: String,

    @ColumnInfo(name = "age")
    var age:Int = 0
)