package com.arturostrowski.quiz.app.data.network.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetLeaderboardRequest (

        @Expose
        @SerializedName("UserID")
        var userID: Long,

        @Expose
        @SerializedName("SecurityToken")
        var securityToken: String

)