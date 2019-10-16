package com.arturostrowski.quiz.app.data.network.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AddFriendRequest (

        @Expose
        @SerializedName("UserID")
        var userID: Long,

        @Expose
        @SerializedName("FriendID")
        var friendID: Long,

        @Expose
        @SerializedName("SecurityToken")
        var securityToken: String

)