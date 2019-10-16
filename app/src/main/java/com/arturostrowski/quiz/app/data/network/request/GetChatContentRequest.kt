package com.arturostrowski.quiz.app.data.network.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetChatContentRequest (

        @Expose
        @SerializedName("UserID")
        var userID: Long,

        @Expose
        @SerializedName("SecurityToken")
        var securityToken: String,

        @Expose
        @SerializedName("ChatID")
        var chatID: Long,

        @Expose
        @SerializedName("Timestamp")
        var timestamp: Long

)