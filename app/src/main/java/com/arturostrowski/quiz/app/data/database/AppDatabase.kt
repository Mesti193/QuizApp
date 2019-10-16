package com.arturostrowski.quiz.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.database.repository.user.UserDao

@Database(entities = [(User::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}