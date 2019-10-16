package com.arturostrowski.quiz.app.data.database.repository.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class User (
        @Expose
        @PrimaryKey
        var id: Long,

        @Expose
        @SerializedName("UserID")
        @ColumnInfo(name = "userID")
        var userID: Long,

        @Expose
        @SerializedName("FirstName")
        @ColumnInfo(name = "firstName")
        var firstName: String,

        @Expose
        @SerializedName("LastName")
        @ColumnInfo(name = "lastName")
        var lastName: String,

        @Expose
        @SerializedName("Email")
        @ColumnInfo(name = "email")
        var email: String,

        @Expose
        @SerializedName("Picture")
        @ColumnInfo(name = "picture")
        var picture: String,

        @Expose
        @SerializedName("Level")
        @ColumnInfo(name = "level")
        var level: Long,

        @Expose
        @SerializedName("Points")
        @ColumnInfo(name = "points")
        var points: Long,

        @Expose
        @SerializedName("Coins")
        @ColumnInfo(name = "coins")
        var coins: Long,

        @Expose
        @SerializedName("SecurityToken")
        @ColumnInfo(name = "securityToken")
        var securityToken: String

)