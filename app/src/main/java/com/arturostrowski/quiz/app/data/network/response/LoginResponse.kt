package com.arturostrowski.quiz.app.data.network.response

import com.google.gson.annotations.SerializedName
import com.arturostrowski.quiz.app.data.database.repository.user.User

data class LoginResponse (

        @SerializedName("Success")
        var success: Boolean,
        @SerializedName("User")
        var user: User,
        @SerializedName("Error")
        var error: Error?

)

//data class User (
//
//        @SerializedName("UserID")
//        var userID: Long,
//        @SerializedName("FirstName")
//        var firstName: String,
//        @SerializedName("LastName")
//        var lastName: String,
//        @SerializedName("Email")
//        var email: String,
//        @SerializedName("SecurityToken")
//        var securityToken: String
//
//)