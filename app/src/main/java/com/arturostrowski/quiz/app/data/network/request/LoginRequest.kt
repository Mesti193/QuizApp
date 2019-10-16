package com.arturostrowski.quiz.app.data.network.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginRequest (

        @Expose
        @SerializedName("Email")
        var email: String,

        @Expose
        @SerializedName("Password")
        var password: String

)