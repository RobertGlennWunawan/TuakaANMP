package com.ubayadev.tuakaanmp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)
    @Query("SELECT * FROM user WHERE username = :username")
    fun selectUser(username: String): User?
}