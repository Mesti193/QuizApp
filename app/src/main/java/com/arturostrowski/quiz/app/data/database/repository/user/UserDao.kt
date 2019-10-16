package com.arturostrowski.quiz.app.data.database.repository.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM user")
    fun loadUser(): List<User>

    @Query("DELETE FROM user")
    fun logout()

}